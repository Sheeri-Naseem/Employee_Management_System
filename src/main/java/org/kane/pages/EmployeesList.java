package org.kane.pages;

import org.kane.services.EmployeeService;
import org.apache.tapestry5.annotations.Property;
import org.kane.entities.Employee;

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
       this.employeesList = employeeService.getAllEmployees();
    }

   public Employee getEmployeeById(int empId){
        return employeeService.getEmployeeById(empId);
    }


}
