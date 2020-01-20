package level1

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class CalculatorTest extends FlatSpec with Matchers {

  val calculator = new Calculator()

  def randInt = new Random().nextInt()

  // FIXME
  "Calculator" should "handle addition" in {
    val a = randInt
    val b = randInt
    calculator.add(a, b) should equal(a + b)
  }

  /* FIXME
  "Calculator" should "handle subtraction" in {
    val a = randInt
    val b = randInt
    calculator.sub(a, b) should equal(a - b)
  }
  */

  /* FIXME
  "Calculator" should "handle multiplication" in {
    val a = randInt
    val b = randInt
    calculator.mul(a, b) should equal(a * b)
  }
  */

  // FIXME
  "Calculator" should "handle division" in {
    val a = randInt
    val b = randInt
    if (b != 0) {
      calculator.div(a, b) should equal(Some(a / b))
    }
    calculator.div(a, 0) should equal(None)
  }
}