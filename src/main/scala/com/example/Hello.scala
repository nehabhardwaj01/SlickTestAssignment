package com.example
import scala.concurrent.ExecutionContext.Implicits.global

object Hello extends App {

/*
initialize all the databases.
 */
  EmployeeRepo.create
  DependentRepo.create
  ProjectRepo.create

/*
Insert values in databases.
 */
  val listOfEmployees = List(
    Employee(1,"neha",2.00),
    Employee(2,"kapil",5.00),
    Employee(3,"preeti",5.50),
    Employee(4,"laxman",1.50),
    Employee(5,"no One",6.00)
  )

  for(l <- listOfEmployees){
    val result = EmployeeRepo.insert(l)
    val res = result.map(res => s"$res row inserted").recover {
      case ex : Throwable => ex.getMessage
    }
    res.map(println(_))
  }


  val listOfProjects = List(
    Project(1,1,"kapil",5,"neha"),
      Project(2,5,"nishant",2,"neha"),
      Project(3,2,"zeba",1,"neha"),
      Project(6,3,"abc",4,"neha"),
      Project(10,10,"xyz",3,"neha")
  )

  for(l <- listOfProjects){
    val result = ProjectRepo.insert(l)
    val res = result.map(res => s"$res row inserted").recover {
      case ex : Throwable => ex.getMessage
    }
    res.map(println(_))
  }

  val listOfDependents = List(
    Dependent(1,"neha",2,"junior",Some(5)),
    Dependent(2,"kapil",1,"assistant",Some(25)),
    Dependent(3,"laxman",4,"intern",Some(20)),
    Dependent(4,"nishant",5,"intern",Some(30)),
    Dependent(5,"zeba",7,"junior",Some(23))
  )

  for(l <- listOfEmployees){
    val result = EmployeeRepo.insert(l)
    val res = result.map(res => s"$res row inserted").recover {
      case ex : Throwable => ex.getMessage
    }
    res.map(println(_))
  }

  /*
  Print all the values in the database
   */
  val getallEmployee = EmployeeRepo.getall().map(res => s"$res \n").recover{
    case ex : Throwable => ex.getMessage
  }
  getallEmployee.map(println(_))

  val getallProjects = ProjectRepo.getall().map(res => s"$res \n").recover{
    case ex : Throwable => ex.getMessage
  }
  getallProjects.map(println(_))


  val getallDependent = DependentRepo.getall().map(res => s"$res \n").recover{
    case ex : Throwable => ex.getMessage
  }
  getallDependent.map(println(_))


  Thread.sleep(10000)
}
