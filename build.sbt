name := """SlickApp"""

version := "1.0"

scalaVersion := "2.12.1"

// Change this to another test framework if you prefer
//libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
//
//libraryDependencies ++= Seq(
//  "com.typesafe.slick" %% "slick" % "3.2.0",
//  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
//  "org.postgresql" % "postgresql" % "9.4.1212",
//  "mysql" % "mysql-connector-java" % "5.1.34"
//  "com.typesafe.play" %% "play-slick" % "1.1.0",
//  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.0"
//)

//ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

//libraryDependencies ++= Seq(
//  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
//  "com.typesafe.slick" %% "slick" % "3.2.0",
//  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
//  "org.slf4j" % "slf4j-nop" % "1.6.4",
//  "org.postgresql" % "postgresql" % "9.4.1212",
//  "org.scoverage" % "scalac-scoverage-plugin_2.11" % "0.99.7",
//  "mysql" % "mysql-connector-java" % "5.1.34"
//)
//
//libraryDependencies += "com.h2database" % "h2" % "1.4.193"



libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.postgresql" % "postgresql" % "9.4.1212",
  "mysql" % "mysql-connector-java" % "5.1.34",
  "com.h2database" % "h2" % "1.4.193"


)
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1"