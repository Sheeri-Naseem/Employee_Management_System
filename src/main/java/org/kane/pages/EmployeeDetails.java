package org.kane.pages;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.http.services.Response;
import org.kane.entities.Role;
import org.kane.services.EmployeeService;
import org.apache.tapestry5.annotations.Property;
import org.kane.entities.Employee;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class EmployeeDetails {

    @PageActivationContext
    private int empId;

    @Property
    private Employee employee;

    @Inject
    private EmployeeService employeeService;

    @SessionState
    private Employee loggedInEmployee;

    @Inject
    private Request request;

    @Inject
    private Response response;

    void onActivate(int empId){

        this.empId = empId;
        this.employee = employeeService.getEmployeeById(empId);

    }

    public boolean isBirthday(){
        if(employee==null || employee.getDateOfBirth() == null){
            return false;
        }

        LocalDate today = LocalDate.now();
        return today.getDayOfMonth() == employee.getDateOfBirth().getDayOfMonth() &&
                today.getMonth() == employee.getDateOfBirth().getMonth() && (employee.getEmpId()==loggedInEmployee.getEmpId());
    }

    public String getFullAddress(){
        if(employee.getAddress()!=null){
            return employee.getAddress().getStreet()+", "+
                    employee.getAddress().getCity()+", "+
                    employee.getAddress().getState()+" - "+
                    employee.getAddress().getPinCode();
        }
        return " Not Available ! ";
    }

    public String getEmployeeRole(){
        if(employee.getRole() != null){
            return employee.getRole().getRoleName();
        }

        return "No Role Assigned Yet ! ";
    }

    public boolean hasEditPermission(){

        //this will return whether the logged in emp has edit permission or not
        return employeeService.hasEditPerm(loggedInEmployee, "edit") && (employee.getEmpId()==loggedInEmployee.getEmpId());

    }
    public boolean isLoggedIn(){
        System.out.println(loggedInEmployee!=null);
        return loggedInEmployee!=null;
    }

    void onActionFromLogoutLink() {
        loggedInEmployee = null;
        try {
            response.sendRedirect(request.getContextPath() + "/index");
        } catch (Exception e) {
            throw new RuntimeException("Redirection failed");
        }
    }
}

