package ssssss

import com.twitter.util.{Await, Future}
import org.scalatest.{FlatSpec, Matchers}
import io.catbird.util.twitterFutureInstance

import scala.collection.mutable

class SearchServiceTest extends FlatSpec with Matchers {

  import SearchServiceTestDoubles._

  "SearchService" should "index a known site" in {

    val output: mutable.HashSet[(Term, Url)] = mutable.HashSet.empty

    val searchService =
      new SearchService(retrieve(output), storage(output), fetch);

    Await.result(
      searchService.fetchAndIndex(Url("www.google.com")))

    output.map(_._1.value) should equal(Set("html", "i", "m", "another", "search", "engine"))
  }

  "SearchService" should "retrieve a term in an indexed site" in {

    val output: mutable.HashSet[(Term, Url)] = mutable.HashSet.empty

    val searchService =
      new SearchService(retrieve(output), storage(output), fetch);

    val result =
      Await.result(
        for {
          _ <- searchService.fetchAndIndex(Url("lambda-the-ultimate.org"))
          result <- searchService.retrieve(Term("languages"))
        } yield result
      )

    result should equal(Seq(Url("lambda-the-ultimate.org")))
  }
}

object SearchServiceTestDoubles {

  def retrieve(output: mutable.Set[(Term, Url)])
              (term: Term): Future[List[Url]] =
    Future {
      output.toList filter (_._1 == term) map (_._2)
    }

  def storage(output: mutable.Set[(Term, Url)])
             (term: Term, url: Url): Future[Unit] =
    Future {
      output.add((term, url))
      ()
    }

  def fetch(url: Url): Future[Contents] =
    url match {
      case Url("www.google.com") => Future(Contents("<html>I'm another search engine</html>"))
      case Url("lambda-the-ultimate.org") => Future(Contents("<html>I'm a languages blog</html>"))
      case url => Future(throw new Exception(s"Unknown url: $url"))
    }
}