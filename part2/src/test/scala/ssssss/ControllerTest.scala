package ssssss

import com.twitter.finagle.http.{Method, Request}
import com.twitter.util.{Await, Future}
import org.scalatest.{FlatSpec, Matchers}

class ControllerTest extends FlatSpec with Matchers {

  import ControllerTestDoubles._

  private val controller =
    Controller.create(retrieveImpl, fetchAndIndex)

  "Controller" should "serve search results on GET /search/{term}" in {
    Await.result(controller(Request(Method.Get, "/search/engine")) map (_.contentString)) should equal {
      """[
        |  {
        |    "value" : "www.google.com"
        |  }
        |]""".stripMargin
    }
  }

  "Controller" should "handle empty results on GET search/{term}" in {
    Await.result(controller(Request(Method.Get, "/search/unknown")) map (_.contentString)) should equal {
      """[
        |]""".stripMargin
    }
  }

  // FIXME
  "Controller" should "add documents on POST /add-doc/" in {
    val req = Request(Method.Post, "/add-doc")
    req.setContentString(
      """
      https://www.bing.com
      https://www.google.com
    """.stripMargin)
    Await.result(controller(req)).contentString should equal {
      "Indexed: 2 docs"
    }
  }

  "Controller" should "report unfound route" in {
    val req = Request(Method.Get, "/no-route-here")
    Await.result(controller(req)).contentString should equal {
      "Could not find route for request GET /no-route-here"
    }
  }

}

object ControllerTestDoubles {

  def retrieveImpl(term: Term): Future[Seq[Url]] =
    Future {
      term.value match {

        case "html" | "i" | "m" =>
          Seq(Url("www.google.com"), Url("lambda-the-ultimate.org"))

        case "another" | "search" | "engine" =>
          Seq(Url("www.google.com"))

        case "languages" | "blog" =>
          Seq(Url("lambda-the-ultimate.org"))

        case _ => Seq.empty
      }
    }

  def fetchAndIndex(url: Url): Future[Unit] =
    Future(())
}
