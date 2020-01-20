name := "part-1"
version := "0.1"
scalaVersion := "2.12.8"

cancelable in Global := true

/*
 * ConcurrentMultiMap implementation
 */
val akkaVersion = "2.6.0"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion

/*
 * JSON encoding/decoding
 */
val circeVersion = "0.11.1"
libraryDependencies += "io.circe" %% "circe-core"    % circeVersion
libraryDependencies += "io.circe" %% "circe-generic" % circeVersion
libraryDependencies += "io.circe" %% "circe-parser"  % circeVersion

/*
 * Web server & Future implementation
 */
val finagleVersion = "19.12.0"
libraryDependencies += "com.twitter" %% "finagle-http" % finagleVersion
libraryDependencies += "io.catbird" %% "catbird-finagle" % finagleVersion

/*
 * Testing
 */
val scalatestVersion =  "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % scalatestVersion % "test"
