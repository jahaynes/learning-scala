package level4

import com.twitter.util.{Await, Future}
import org.scalatest.{FlatSpec, Matchers}

class ForComprehensionsTest extends FlatSpec with Matchers {

  import ForComprehensions._

  "sum" should "add the elements" in {
    sum(List(1, 2, 3)) should equal(6)
  }

  "nested sum" should "add all the (sub)elements" in {
    nestedSum(
      List(
        List(1, 2, 3),
        List(4, 5, 6),
        List(7, 8, 9))
    ) should equal(45)
  }

  "future sum" should "work" in {
    Await.result(futureSum(Future(1), Future(2))) should equal(3)
  }
}
