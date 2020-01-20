package level2

import org.scalatest.{FlatSpec, Matchers}

class BinarySearchTreeTest extends FlatSpec with Matchers {

  "An empty BinarySearchTree" should "have size 0" in {
    val tree = new Empty[Int]
    tree.size should equal(0)
  }

  // FIXME
  "An empty BinarySearchTree" should "not contain anything" in {
    val tree = new Empty[Int]
    tree.contains(2) should equal(false)
  }

  // FIXME
  "An empty BinarySearchTree" should "implement contains" in {
    val tree = new Empty[Int].insert(3)
    tree.contains(2) should equal(false)
    tree.contains(3) should equal(true)
  }

  // FIXME
  "BinarySearchTree" should "allow insertion" in {
    val tree = new Empty[Int]
    val inserted = tree.insert(3)
    inserted.contains(3) should equal(true)
  }

  "BinarySearchTree" should "implement size" in {
    val tree = new Empty[Int]
    tree.size should equal(0)
    tree
      .insert(4)
      .insert(2)
      .insert(5)
      .size should equal(3)
  }

  "BinarySearchTree" should "not duplicate repeated insertions" in {

    val tree = new Empty[Int]

    tree
      .insert(5)
      .insert(6)
      .insert(6)
      .insert(5)
      .size should equal(2)
  }
}

