package level3

object HigherOrderFunctions {

  /** FIXME: implement
   * Finds the shortest String in a Sequence of Strings,
   * or None if the input is empty
   *
   * Hint: you may want to use Seq.minBy
   *
   * */
  def shortestString(strings: Seq[String]): Option[String] =
    strings match {
      case Seq()    => throw new RuntimeException("shortestString(Seq()) not implemented!")
      case nonEmpty => throw new RuntimeException("shortestString(strings) not implemented!")
    }

  /**
   * FIXME: implement
   *
   * Finds the words in text which don't contain 'e'
   * and returns them lower-cased and in alphabetical order.
   *
   * Hint: You may want to use:
   *  Seq.filter
   *  Seq.map
   *  String.startsWith
   *  String.toLowerCase
   *  words()
   */
  def wordsBeginningWithFInLowerCase(text: String): Seq[String] =
    throw new RuntimeException("HigherOrderFunctions.wordsBeginningWithFInLowerCase not implemented!")

  private def words(text: String): Seq[String] =
    StringUtils.splitKeep(text, Character.isAlphabetic(_))
}
