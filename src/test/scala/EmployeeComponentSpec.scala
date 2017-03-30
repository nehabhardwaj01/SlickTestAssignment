import com.example.{H2DbProvider, Employee, MySqlDBProvider, EmployeeComponent}
import org.scalatest.{Matchers, AsyncFunSuite}
import org.scalatest.concurrent.PatienceConfiguration.Timeout

import scala.collection.mutable
import scala.concurrent.Await

object employeeComponent extends EmployeeComponent with H2DbProvider

  class EmployeeComponentSpec extends AsyncFunSuite {

    test("Add an Employee ") {
      employeeComponent.insert(Employee(17, "prashant", 32)).map( x =>assert(x == 1))
    }

    /*
    The following test will not b able to update the record with id:17 as it is was temporarily inserted earlier while testing.
     */
    test("should not update Employee record ") {
      employeeComponent.update(17,"New Name").map(x=>assert(x == 0))
    }

    /*
    This test will execute on the initial data provided.
     */
    test("should update Employee record ") {
      employeeComponent.update(6,"Neha again").map(x=>assert(x == 1))
    }

    test("delete Employee by experience") {
      employeeComponent.delete(23).map(x=>assert(x == 0 ))
    }

    test("get all employees") {
      employeeComponent.getall.map( x => assert( x.size == 4))
    }

    test("insert a new employee or update an existing employee") {
      employeeComponent.insertOrUpdate(Employee(17,"Prashant Again",36)).map(x => assert(x==1))
    }
  }


