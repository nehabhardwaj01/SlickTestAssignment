package com.example

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import Binding.db

case class Dependent(depId : Int,name : String,dependentOn : Int,relation : String,age :Option[Int] )


trait DependentTable extends EmployeeTable{

  private[example] class DependentTable(tag  :Tag) extends Table[Dependent](tag,"employee_dependent"){
    val depId = column[Int]("dep_id",O.PrimaryKey)
    val name = column[String]("name")
    val dependentOn = column[Int]("dependent_on")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")

    def employeeDepdndentFK = foreignKey("employee_dependent_fk",dependentOn,queryObj)(_.empId)

    def * = (depId,name,dependentOn,relation,age) <>(Dependent.tupled,Dependent.unapply)
  }

  val dependentTableQuery = TableQuery[DependentTable]

}


trait DependentComponent extends DependentTable{

  def create = db.run(dependentTableQuery.schema.create)

  def insert(dependent :Dependent) = {
      db.run(dependentTableQuery += dependent)
  }

  def deleteByAge(age : Int) ={
    val query= dependentTableQuery.filter(x => x.age < 16)
    val action = query.delete
    db.run(action)
  }

  def deleteById(id : Int) ={
    val query= dependentTableQuery.filter(x => x.depId === id)
    val action = query.delete
    db.run(action)
  }

  def update(id:Int,name : String) ={
    val query = dependentTableQuery.filter(_.depId === id).map(_.name).update(name)
    db.run(query)
  }

  def getall() ={
    db.run(dependentTableQuery.result)
  }

  def insertOrUpdate(dep : Dependent) = {
    db.run(dependentTableQuery.insertOrUpdate(dep))
  }

}

object DependentRepo extends DependentComponent{

}
