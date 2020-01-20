package ssssss

import cats.MonadError
import cats.implicits._
import com.twitter.finagle.http.{Request, RequestBuilder, Response}

class WebClient[F[_]](buildClient: (UseTls, Host) => Request => F[Response])
                     (implicit me: MonadError[F, Throwable]) {

  def fetch(url: Url): F[Contents] =
    for {
      req   <- me.pure(buildRequest(url))
      host  <- findHost(req)
      useTls = if (url.value.startsWith("https://")) Tls() else NoTls()
      client = buildClient(useTls, host)
      resp  <- client(req)
    } yield Contents(resp.contentString)

  private def buildRequest(url: Url): Request =
    RequestBuilder().url(url.value).buildGet()

  private def findHost(req: Request): F[Host] =
    req.host match {
      case Some(h) => me.pure(Host(h))
      case None => me.raiseError(new Exception("No host found in request"))
    }
}

object WebClient {

  import com.twitter.finagle.Http
  import com.twitter.finagle.http.{Request, Response}
  import com.twitter.util.Future

  def create: WebClient[Future] =
    new WebClient[Future](buildClient)(io.catbird.util.twitterFutureInstance)

  private def buildClient(useTls: UseTls,
                          host: Host): Request => Future[Response] =
    useTls match {
      case Tls() =>
        Http
          .client
          .withTls(host.value)
          .newService(s"${host.value}:443")

      case NoTls() =>
        Http
          .client
          .newService(s"${host.value}:80")
    }
}