package com.example

import slick.jdbc.{PostgresProfile, JdbcProfile, MySQLProfile}

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
