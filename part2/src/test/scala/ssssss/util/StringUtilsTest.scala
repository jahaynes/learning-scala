package ssssss.util

import org.scalatest.{FlatSpec, Matchers}

class StringUtilsTest extends FlatSpec with Matchers {

  import StringUtils.{linesOfString, splitOn}

  "linesOfString" should "work on empty strings" in {
    linesOfString("") should equal (Seq.empty)
  }

  "linesOfString" should "work on spaces" in {
    linesOfString("   ") should equal (Seq("   "))
  }

  "linesOfString" should "cut up CR-style input" in {
    linesOfString("abc\rdef") should equal (Seq("abc", "def"))
  }

  "linesOfString" should "cut up LF-style input" in {
    linesOfString("abc\ndef") should equal (Seq("abc", "def"))
  }

  "linesOfString" should "cut up CRLF-style input" in {
    linesOfString("abc\r\ndef") should equal (Seq("abc", "def"))
  }

  "linesOfString" should "cut mixed-style input" in {
    linesOfString("abc\r\ndef\rghi\nklm") should equal (Seq("abc", "def", "ghi", "klm"))
  }

  "linesOfString" should "include empty lines" in {
    linesOfString("abc\n\ndef") should equal (Seq("abc", "", "def"))
  }

  "splitOn" should "handle empty input" in {
    splitOn("", _ => true) should equal (Seq.empty)
  }

  "splitOn" should "not drop its first match" in {
    splitOn("abc_def", c => c.isLetterOrDigit).head should equal ("abc")
    splitOn("_abc_def", c => c.isLetterOrDigit).head should equal ("abc")
  }

  "splitOn" should "not drop its last match" in {
    splitOn("abc_def", c => c.isLetterOrDigit).last should equal ("def")
    splitOn("abc_def_", c => c.isLetterOrDigit).last should equal ("def")
  }

  "splitOn" should "not insert unnecessary breaks" in {
    splitOn("abc123def", c => c.isLetter) should equal (Seq("abc", "def"))
  }
}
