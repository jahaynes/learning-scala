package ssssss

case class Term(value: String) extends AnyVal

case class Url(value: String) extends AnyVal

case class Contents(value: String) extends AnyVal

case class Host(value: String) extends AnyVal

sealed trait UseTls

case class Tls() extends UseTls

case class NoTls() extends UseTls