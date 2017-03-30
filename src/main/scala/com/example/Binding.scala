package com.example

import java.util.UUID

import slick.jdbc.{H2Profile, PostgresProfile, JdbcProfile, MySQLProfile}

trait DbProvider {

  val driver:JdbcProfile

  import driver.api._

  val db:Database

}

trait PostGresDBProvider extends DbProvider{

  val driver = PostgresProfile

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}

trait MySqlDBProvider extends DbProvider{

  val driver = MySQLProfile

  import driver.api._

  val db = Database.forConfig("mySqlDB")

}

trait H2DbProvider extends DbProvider{

  val driver = H2Profile

  import driver.api._

  val randomDB = "jdbc:h2:mem:test" +
    UUID.randomUUID().toString + ";"
  val h2Url = randomDB + "MODE=MySql;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM 'src/test/resources/schema.sql'\\;RUNSCRIPT FROM 'src/test/resources/initialdata.sql'"
  println(s"\n\n~~~~~~~~~~~~~~~~~~~~~             $h2Url         ~~~~~~~~~~~~~~~~~~~~~~~\n\n")
  val db: Database = Database.forURL(url = h2Url, driver = "org.h2.Driver")
}
