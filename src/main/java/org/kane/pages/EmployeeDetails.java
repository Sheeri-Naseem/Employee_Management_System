package org.kane.pages;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.kane.entities.Role;
import org.kane.services.EmployeeService;
import org.apache.tapestry5.annotations.Property;
import org.kane.entities.Employee;

import javax.inject.Inject;
import java.util.stream.Collectors;

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

    public String getFullAddress(){
        if(employee.getAddress()!=null){
            return employee.getAddress().getStreet()+", "+
                    employee.getAddress().getCity()+", "+
                    employee.getAddress().getState()+" - "+
                    employee.getAddress().getPinCode();
        }
        return " No Available ! ";
    }

    public String getEmployeeRoles(){
        if(employee.getRoles()!=null && !employee.getRoles().isEmpty()){
            return employee.getRoles().stream()
                    .map(Role::getRoleName)
                    .collect(Collectors.joining(", "));
        }

        return "No Roles Assigned Yet ! ";
    }
}

