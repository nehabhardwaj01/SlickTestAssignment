package com.example


case class Employee(empId : Int,name : String,experience : Double)

trait EmployeeTable {
  this: DbProvider =>

  import driver.api._


  private[example] class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee") {
    val empId = column[Int]("emp_id", O.PrimaryKey)
    val name = column[String]("name")
    val experience = column[Double]("experience")

    def * = (empId, name, experience) <>(Employee.tupled, Employee.unapply)
  }

  val employeeTableQuery = TableQuery[EmployeeTable]
}

trait EmployeeComponent extends EmployeeTable{

  this:DbProvider =>
  import driver.api._

  def create = {
    db.run(employeeTableQuery.schema.create)
    employeeTableQuery.schema.createStatements.foreach(println)
  }

  def insert(emp :Employee) = {
    db.run(employeeTableQuery += emp)
  }

  def delete(exp : Double) ={
    val query= employeeTableQuery.filter(x => x.experience < 4.00)
    val action = query.delete
    db.run(action)
  }

  def update(id:Int,name : String) ={
    val query = employeeTableQuery.filter(_.empId === id).map(_.name).update(name)
    db.run(query)
  }

  def getall() ={
    db.run(employeeTableQuery.result)
  }

  def insertOrUpdate(emp : Employee) = {
    db.run(employeeTableQuery.insertOrUpdate(emp))
  }

  def drop = {
    db.run(employeeTableQuery.schema.drop)
    employeeTableQuery.schema.drop.statements.foreach(println)
  }

  def delete = {
    db.run(employeeTableQuery.map(x => x).delete)
  }
}

object EmployeeComponent extends EmployeeComponent with MySqlDBProvider{

}