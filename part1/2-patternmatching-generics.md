## Pattern Matching

If you build up data using _case classes_
```scala
trait Address
case class HostName(value: String) extends Address
case class IpAddress(value: Array[Byte]) extends Address
```
then you can _pattern match_ on it.
```scala
def describe(address: Address) =
  address match {
    case HostName(value)  => println(s"address is a hostname: $value")
    case IpAddress(value) => println(s"address is an ip address: $value")
  }
```

## Everything is an expression

Other languages usually have statements and expressions.  Scala just has expressions.

For example, Java:
```java
int x;
if (b) {
  x = 1;
} else {
  x = 2;
}
```
vs Scala:
```scala
val x =
    if (b) {
      1
    } else {
      2
    }
```

All Scala functions have a return type.  Where other languages use `void` to signal that nothing returns, Scala uses the type `Unit` which only has one value `()` also pronounced "unit", e.g.

```scala
def println(x: Any): Unit
```

## Generics and Type Variables
Some data types are parametrised by _type variables_, usually single upper case letters, e.g.
```scala
Seq[A]
```
* The point of this is for the implementer of Seq to _not know_ what type of data will be stored:
    * If the implementer doesn't know the type of the stored data, then (s)he cannot hardcode things or introduce bugs specific to certain data types.
    * So a test which verifies `Seq[Int]` will also verify `Seq[String]`.

