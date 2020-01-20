## For-comprehensions

Most languages have for-loops.  A for-loop is useful if you want to work with _many_ items.

But what if instead of _many_ items, you have an item _in the future_, or an item _which may be missing_, etc?  A for-loop can't help you iterate over future items or missing items.

Scala provides for-comprehensions (instead of for-loops) that handle all these use cases (not just the _many_ item case).

*Very few languages* have something like this.

Using a for-comprehension with lists:
```scala
val result =
  for {
    x <- List(1, 3, 5)
    y <- List(7, 12)
  } yield (x, y)

  println(result) // List((1,7), (1,12), (3,7), (3,12), (5,7), (5,12))
```

Using a for-comprension with Option:
```scala
val y =
  for {
    f <- Some((x: Int) => x + 2)
    x <- Some(5)
  } yield f(x)

println(y) // Some(7)
```
