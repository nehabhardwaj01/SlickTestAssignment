package com.example

import slick.jdbc.PostgresProfile.api._
import Binding.db


case class Project(pId : Int,empId : Int,name : String,members : Int,lead :String)


trait ProjectTable extends EmployeeTable{


  private[example] class ProjectTable(tag  :Tag) extends Table[Project](tag,"projects"){
    val pId = column[Int]("p_id", O.PrimaryKey)
    val empId = column[Int]("emp_id")
    val name = column[String]("name")
    val members = column[Int]("team_members")
    val lead = column[String]("lead")

    def employeeProjectFK = foreignKey("employee_project_fk",empId,queryObj)(_.empId)

    def * = (pId,empId,name,members,lead) <>(Project.tupled,Project.unapply)
  }

  val projectTableQuery = TableQuery[ProjectTable]
}

trait ProjectComponent extends ProjectTable {

  def create = db.run(projectTableQuery.schema.create)

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
}

object ProjectRepo extends ProjectComponent{

}