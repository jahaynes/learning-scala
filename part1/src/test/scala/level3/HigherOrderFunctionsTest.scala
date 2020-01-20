package level3

import level3.HigherOrderFunctions._
import org.scalatest.{FlatSpec, Matchers}

class HigherOrderFunctionsTest extends FlatSpec with Matchers {

  "The shortest of no strings" should "be None" in {
    shortestString(Seq()) should equal(None)
  }

  "shortestString" should "return the shortest string" in {
    shortestString(Seq("3455", "123", "234325")) should equal(Some("123"))
  }

  "sortedWordsBeginningWithFLowerCased" should "process text" in {

    val text =
      """
        |Lorem Ipsum är en utfyllnadstext från tryck- och förlagsindustrin.
        |Lorem ipsum har varit standard ända sedan 1500-talet, när en okänd boksättare tog
        |att antal bokstäver och blandade dem för att göra ett provexemplar av en bok.
        |Lorem ipsum har inte bara överlevt fem århundraden, utan även övergången till
        |elektronisk typografi utan större förändringar.
        |Det blev allmänt känt på 1960-talet i samband med lanseringen av
        |Letraset-ark med avsnitt av Lorem Ipsum, och senare med mjukvaror som Aldus PageMaker.
        |""".stripMargin

    wordsBeginningWithFInLowerCase(text).toSet should equal {
      Set("fem", "från", "för", "förlagsindustrin", "förändringar")
    }
  }

  "sdf" should "Sdds" in {

    val y =
      for {
        f <- Some((x: Int) => x + 2)
        x <- Some(5)
      } yield f(x)

    println(y)

  }
}