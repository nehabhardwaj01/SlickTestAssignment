package com.example

case class Project(pId : Int,empId : Int,name : String,members : Int,lead :String)


trait ProjectTable extends EmployeeTable{
  this:DbProvider =>
  import driver.api._


  private[example] class ProjectTable(tag  :Tag) extends Table[Project](tag,"projects"){
    val pId = column[Int]("p_id", O.PrimaryKey)
    val empId = column[Int]("emp_id")
    val name = column[String]("name")
    val members = column[Int]("team_members")
    val lead = column[String]("lead")

    def employeeProjectFK = foreignKey("employee_project_fk",empId,employeeTableQuery)(_.empId)

    def * = (pId,empId,name,members,lead) <>(Project.tupled,Project.unapply)
  }

  val projectTableQuery = TableQuery[ProjectTable]
}

trait ProjectComponent extends ProjectTable {
  this:DbProvider =>
  import driver.api._

  def create = {
    db.run(projectTableQuery.schema.create)
    projectTableQuery.schema.createStatements.foreach(println)
  }

  def insert(prj :Project) = db.run(
    projectTableQuery += prj
  )

  def delete(exp : Double) ={
    val query= projectTableQuery.filter(x => x.name startsWith("n"))
    val action = query.delete
    db.run(action)
  }

  def update(id:Int,name : String) ={
    val query = projectTableQuery.filter(_.pId === id).map(_.name).update(name)
    db.run(query)
  }

  def getall() ={
    db.run(projectTableQuery.result)
  }

  def insertOrUpdate(prj : Project) = {
    db.run(projectTableQuery.insertOrUpdate(prj))
  }

  def drop = {
    db.run(projectTableQuery.schema.truncate)
    projectTableQuery.schema.truncate.statements.foreach(println)
  }
}

object ProjectComponent extends ProjectComponent with MySqlDBProvider{

}