package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.entities.Employee;
import org.services.EmployeeService;

import javax.inject.Inject;
import java.util.List;


public class EmployeesList {

    @Property
    Employee employee;

    @Property
    List<Employee> employeesList ;

    @Inject
    EmployeeService employeeService;

    void setupRender() {

       employeesList = employeeService.getAllEmployees();

//        employeesList = new ArrayList<>();
//        employee =  new Employee(1, "Ankit", 35, "Mishra", "Mumbai");
//        employeesList.add(employee);
//        employee = new Employee(2, "Aishwarya", 30, "Verma", "Gurgaon");
//        employeesList.add(employee);
//        employee = new Employee(3, "Nandini", 40, "Daksh", "Noida");
//        employeesList.add(employee);
//        employee = new Employee(4, "Urvashi", 25, "Singh", "Delhi");
//        employeesList.add(employee);
    }

   public Employee getEmployeeById(int empId){

        return employeeService.getEmployeeById(empId);
    }


}
