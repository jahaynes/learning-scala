package level2

abstract class Tree[A] {

  def insert(a: A): Tree[A]

  def contains(a: A): Boolean

  def size: Int
}

class Empty[A <% Ordered[A]] extends Tree[A] {

  override def insert(a: A): Tree[A] =
    new Node(a, new Empty[A], new Empty[A])

  override def contains(a: A): Boolean = false

  override def size: Int = 0
}

class Node[A <% Ordered[A]](val value: A,
                            val left: Tree[A],
                            val right: Tree[A]) extends Tree[A] {

  override def insert(a: A): Tree[A] =
    if (a == value) {
      this
    } else if (a > value) {
      new Node(value, left, right.insert(a))
    } else {
      new Node(value, left.insert(a), right)
    }

  //FIXME: implement contains
  override def contains(a: A): Boolean =
    throw new RuntimeException("BinarySearchTree.contains not implemented")

  //FIXME: implement size
  override def size: Int =
    throw new RuntimeException("BinarySearchTree.size not implemented")
}
