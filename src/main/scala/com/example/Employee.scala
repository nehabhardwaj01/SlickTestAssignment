package com.example


case class Employee(empId : Int,name : String,experience : Double)

trait EmployeeTable{
  this:DbProvider =>
  import driver.api._


  private[example] class EmployeeTable(tag  :Tag) extends Table[Employee](tag,"employee"){
    val empId = column[Int]("emp_id",O.PrimaryKey)
    val name = column[String]("name")
    val experience = column[Double]("experience")

    def * = (empId,name,experience) <>(Employee.tupled,Employee.unapply)
  }

  val queryObj = TableQuery[EmployeeTable]
}

trait EmployeeComponent extends EmployeeTable{

  this:DbProvider =>
  import driver.api._

  def create = db.run(queryObj.schema.create)

  def insert(emp :Employee) = db.run{
    queryObj += emp
  }

  def delete(exp : Double) ={
    val query= queryObj.filter(x => x.experience < 4.00)
    val action = query.delete
    db.run(action)
  }

  def update(id:Int,name : String) ={
    val query = queryObj.filter(_.empId === id).map(_.name).update(name)
    db.run(query)
  }

  def getall() ={
    db.run(queryObj.result)
  }

  def insertOrUpdate(emp : Employee) = {
    db.run(queryObj.insertOrUpdate(emp))
  }
}

object EmployeeComponent extends EmployeeComponent with MySqlDBProvider{

}