## Functions
* Functions have names, inputs and an output.
* `;` and `return` are not needed.

```scala
def add(a: Int, b: Int): Int = {
  a + b
}
```
* `{}` are not needed for single expressions.
* return types are not always needed.
```scala
def add(a: Int, b: Int) =
  a + b
```

## Variables

* `val` is the most common way to declare a variable (immutable).
* `var` is like `val` but is mutable.
* `lazy val` waits until it's called for the first time, then computes its value once.
* `def` defines functions, but can be used for variables too.
*  If you declare variables with `def` then they will be computed on each call, instead of just once:
```scala
val valRandInt = new Random().nextInt()
println(valRandInt)  // 1362238645
println(valRandInt)  // 1362238645
```
```scala
def defRandInt = new Random().nextInt()
println(defRandInt)  // 501742845
println(defRandInt)  // -1012775604
```

## Classes, Case Classes and Objects

### Classes ###
* Classes have members and functions
* There is no constructor, members are put directly in parentheses next to the class name.
```scala
class Storage(items: Set[Item]) {

  def put(item: Item): Unit = {
    ???
  }

  def get(name: String): Item = {
    ???
  }
}
```
You make _instances_ of classes using the `new` keyword, e.g.
```scala
val storage = new Storage(items)
```
Once you have an instance of a class, you can call its functions:
```scala
storage.put(item)
```

### Case Classes ###
If you don't need functions, then use a `case class` instead
```scala
case class Item(name: String)
```
Case classes do not use `new` to instantiate:
```scala
val myItem = Item("foo")
```

### Objects ###
* If you want to run functions without instantiating a class, use an object instead (Scala has no `static` keyword).
* The usual pattern is to have a file `Storage.scala` with a _class_ and a "companion" _object_ (same name as class):

```scala
class Storage(item: Set[Item]) {

  def put(item: Item): Unit = {
    ???
  }

  def get(name: String): Item = {
    ???
  }
}

object Storage {

  def fromString(str: String): Storage = {
    ???
  }
}
```

### Summary ###

`case classes` are for data, `objects` are for code, and `classes` are for both.
