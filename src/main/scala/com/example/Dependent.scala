package com.example

  case class Dependent(depId : Int,name : String,dependentOn : Int,relation : String,age :Option[Int] )


trait DependentTable extends EmployeeTable{

  this:DbProvider =>
  import driver.api._

  private[example] class DependentTable(tag  :Tag) extends Table[Dependent](tag,"employee_dependent"){
    val depId = column[Int]("dep_id",O.PrimaryKey)
    val name = column[String]("name")
    val dependentOn = column[Int]("dependent_on")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")

    def employeeDepdndentFK = foreignKey("employee_dependent_fk",dependentOn,employeeTableQuery)(_.empId)

    def * = (depId,name,dependentOn,relation,age) <>(Dependent.tupled,Dependent.unapply)
  }

  val dependentTableQuery = TableQuery[DependentTable]

}


trait DependentComponent extends DependentTable{
  this:DbProvider =>
  import driver.api._

  def create = {
    db.run(dependentTableQuery.schema.create)
    dependentTableQuery.schema.createStatements.foreach(println)

  }

  def insert(dependent :Dependent) = db.run{
        dependentTableQuery += dependent
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
    db.run(dependentTableQuery.to[List].result)
  }

  def insertOrUpdate(dep : Dependent) = {
    db.run(dependentTableQuery.insertOrUpdate(dep))
  }

  def drop = {
    db.run(dependentTableQuery.schema.drop)
    dependentTableQuery.schema.drop.statements.foreach(println)
  }

}

object DependentComponent extends DependentComponent with MySqlDBProvider{

}
