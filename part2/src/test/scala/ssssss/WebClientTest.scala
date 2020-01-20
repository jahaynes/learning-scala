package ssssss

import com.twitter.finagle.http.path.Path
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.{Await, Future}
import org.scalatest.{FlatSpec, Matchers}

class WebClientTest extends FlatSpec with Matchers {

  implicit val me = io.catbird.util.twitterFutureInstance

  import WebClientTest.buildClient

  "WebClient" should "make requests" in {
    val x = new WebClient[Future](buildClient)
    val f = x.fetch(Url("http://www.google.com"))
    Await.result(f) should equal(Contents("You downloaded from www.google.com at '/' using GET"))
  }
}

object WebClientTest {
  def buildClient(useTls: UseTls, host: Host): Request => Future[Response] =
    req =>
      (req.method, req.host, Path(req.path)) match {
        case (method, Some(host), path) => Future {
          val resp = Response(Status.Ok)
          resp.setContentString(s"You downloaded from $host at '/$path' using $method")
          resp
        }
      }
}


