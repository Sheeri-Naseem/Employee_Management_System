package org.pages;

import org.apache.tapestry5.annotations.Property;
import org.entities.Employee;
import org.services.EmployeeService;

import javax.inject.Inject;

public class EmployeeDetails {


    @Property
    private Employee employee;

    @Inject
    private EmployeeService employeeService;

    void onActivate(int empId){

        employee = employeeService.getEmployeeById(empId);

    }
}
