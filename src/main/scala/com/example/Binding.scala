package com.example

import slick.jdbc.PostgresProfile.api._

object Binding {
  val db = Database.forConfig("myPostgresDB")
  val db1 = Database.forConfig("mySqlDB")
}
