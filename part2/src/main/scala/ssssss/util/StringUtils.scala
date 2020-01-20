package ssssss.util

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object StringUtils {

  def linesOfString(str: String): List[String] = {
    val buf = new ArrayBuffer[String]()
    str.lines().forEach(
      buf.append(_)
    )
    buf.toList
  }

  def splitOn(str: String,
              f: Char => Boolean): List[String] =
    splitOn(Seq.empty, f, str)

  @tailrec
  private final def splitOn(acc: Seq[String],
                            f: Char => Boolean,
                            str: String): List[String] =
    str match {
      case "" => acc.toList
      case _ =>
        val (good, rest) = str.span(f)
        val acc2 = if (good.isEmpty) acc else acc :+ good
        splitOn(acc2, f, rest.dropWhile(!f(_)))
    }
}