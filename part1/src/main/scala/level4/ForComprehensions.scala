package level4

import com.twitter.util.Future

object ForComprehensions {

  def sum(intList: List[Int]): Int = {
    var s = 0
    for {
      i <- intList
    } yield s += i
    s
  }

  // FIXME
  def nestedSum(nestedList: List[List[Int]]): Int =
    throw new RuntimeException("ForComprehensions.nestedSum not implemented")

  // FIXME
  def futureSum(one: Future[Int],
                two: Future[Int]): Future[Int] =
    throw new RuntimeException("ForComprehensions.futureSum not implemented")
}
