package org.kane.pages;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.kane.services.EmployeeService;
import org.apache.tapestry5.annotations.Property;
import org.kane.entities.Employee;

import javax.inject.Inject;

public class EmployeeDetails {

    @PageActivationContext
    private int empId;

    @Property
    private Employee employee;

    @Inject
    private EmployeeService employeeService;

    void onActivate(int empId){

        this.empId = empId;
        this.employee = employeeService.getEmployeeById(empId);

    }

}

