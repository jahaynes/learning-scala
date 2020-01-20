# Why Scala?

## It runs on the JVM

People who like the JVM agree that the JVM is a good platform to run on.

## Managers accept it

* https://www.slideshare.net/Odersky/scala-days-san-francisco-45917092

## Pretty good type system

In general, type systems have various goals:

* Minimise the number of incorrect programs which can be written
* Maximise the number of correct programs which can be written
* Assist the compiler in producing fast output
* Give early feedback to the developer
* Guide the developer towards a solution

In particular, Scala's type system lets you represent some things not representable in other mainstream type systems, e.g.

```scala
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}
```
The F is not representable in Java.
