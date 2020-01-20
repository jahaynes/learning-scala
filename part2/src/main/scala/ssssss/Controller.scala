package ssssss

import cats.Monad
import cats.implicits._
import com.twitter.finagle.http.Method.{Get, Post}
import com.twitter.finagle.http.path.{->, /, Path, Root}
import com.twitter.finagle.http.{Request, Response, Status}
import io.circe.generic.auto._
import io.circe.syntax._
import ssssss.util.StringUtils

class Controller[F[_]](retrieveImpl: Term => F[Seq[Url]],
                       fetchAndIndex: Url => F[Unit])
                      (implicit m: Monad[F]) extends (Request => F[Response]) {

  def apply(req: Request): F[Response] =

    req.method -> Path(req.path) match {

      case Get -> Root / "search" / term =>
        searchTerm(Term(term))

      //FIXME
      case Post -> Root / "add-doc" =>
        val urls = StringUtils.linesOfString(req.contentString).map(Url(_))
        addDoc(urls)

      case _ =>
        m.pure(notFound(req))
    }

  private def searchTerm(term: Term): F[Response] =
    retrieveImpl(term) map {
      urls =>
        val response = Response(Status.Ok)
        response.setContentString(s"${urls.asJson}")
        response
    }

  private def addDoc(docs: List[Url]): F[Response] =
    docs traverse fetchAndIndex map {
      units =>
        val response = Response(Status.Accepted)
        response.setContentString(s"Indexed: ${units.length} docs")
        response
    }

  private def notFound(req: Request): Response = {
    val response = Response(Status.NotFound)
    response.setContentString(s"Could not find route for request ${req.method} ${req.path}")
    response
  }
}

object Controller {

  import com.twitter.finagle.Service
  import com.twitter.util.Future

  def create(retrieveImpl: Term => Future[Seq[Url]],
             fetchAndIndex: Url => Future[Unit]): Service[Request, Response] = {
    val controller = new Controller(retrieveImpl, fetchAndIndex)(m = io.catbird.util.twitterFutureInstance)
    req => controller(req)
  }
}