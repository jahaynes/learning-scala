package level3

import scala.annotation.tailrec

object StringUtils {

  def splitKeep(text: String,
                p: Char => Boolean): Seq[String] = {
    @tailrec
    def go(acc: Seq[String],
           text: String): Seq[String] = {
      val (keep, rest) = text.dropWhile(!p(_)) span p
      if (text.isEmpty) {
        acc
      } else {
        go(if (keep.isEmpty) acc else keep +: acc, rest)
      }
    }

    go(Seq.empty, text)
  }
}
