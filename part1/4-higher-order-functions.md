## Higher-order functions

Functions which accept other functions as parameters are called higher-order functions.

map(..) and foreach(..) are two such examples:

```scala
val multilineString =
  """
    |one two three
    | four five six
    |  seven eight nine
    |""".stripMargin

StringUtils
  .linesOfString(multilineString)
  .map(line => line.trim)               // Here
  .foreach(line => println(line))       // Here
```
