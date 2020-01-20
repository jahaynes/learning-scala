package ssssss

import com.twitter.finagle.Http
import com.twitter.util.Await
import ssssss.store.InMemStore
import io.catbird.util.twitterFutureInstance

/**
 * Super-simple scholastic Scala search service
 */
object Main {

  val port = 8080

  def main(args: Array[String]): Unit = {

    val store =
      InMemStore.create

    val webClient =
      WebClient.create

    val searchService =
      new SearchService(store.retrieve, store.store, webClient.fetch)

    val controller =
      Controller.create(searchService.retrieve, searchService.fetchAndIndex)

    val server =
      Http.serve(s":$port", controller)

    println(s"Serving on port $port")

    Await.ready(server)
  }
}
