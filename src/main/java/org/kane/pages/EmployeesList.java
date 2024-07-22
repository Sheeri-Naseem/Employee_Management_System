package org.kane.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.http.services.Response;
import org.kane.services.EmployeeService;
import org.kane.entities.Employee;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;


public class EmployeesList {

    @Property
    private Employee employee;

    @Inject
    private EmployeeService employeeService;

    @SessionState
    private Employee loggedInEmployee;

    @InjectPage
    private Index index;

    @InjectComponent
    private ActionLink imageLink;

    @Inject
    private Request request;

    @Inject
    private Response response;

    @Property
    private String imageUrl;


    @InjectPage
    private EmployeeImagePage employeeImagePage;

    public Employee getEmployeeById(int empId){
        return employeeService.getEmployeeById(empId);
    }

   public List<Employee> getEmployees(){
       return employeeService.getAllEmployees();
   }

   public String getRole(){
       if(employee.getRole() != null){
           return employee.getRole().getRoleName();
       }
       return "No Role Assigned Yet !";
   }

    @OnEvent(value = "deleteEmployee")
    void deleteEmployee(int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    public boolean hasEditPermission(){

        //this will return whether the logged in emp has edit permission or not
        return employeeService.hasEditPerm(loggedInEmployee, "edit_all");

    }

    public boolean hasDeletePermission(){

        //this will return whether the logged in emp has delete permission or not
        return employeeService.hasDeletePerm(loggedInEmployee, "delete_all");

    }

    public boolean hasCreatePermission(){
        return employeeService.hasEditPerm(loggedInEmployee, "edit_all");
    }

    @OnEvent(value = "promoteToManager")
    void promoteEmployee(int employeeId) {
        if (hasEditPermission()) {
            employeeService.promoteToManager(employeeId);
        }
    }

    void onActionFromImageLink(int employeeId) {
        System.out.println("onActionFromImageLink() called with ID : "+employeeId +"\n");

        Employee emp = employeeService.getEmployeeById(employeeId);
        this.imageUrl = request.getContextPath() + "/images/" + emp.getImageFilename();
        try {
            response.sendRedirect(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image", e);
        }
    }

    @OnEvent(value = EventConstants.ACTION, component = "imageLink")
    void onImageLink(int employeeId) {
        System.out.println("onActionFromImageLink() called with ID : "+employeeId +"\n");

        Employee emp = employeeService.getEmployeeById(employeeId);
        this.imageUrl = request.getContextPath() + "/images/" + emp.getImageFilename();
        employeeImagePage.setImageUrl(imageUrl);
        try {
            response.sendRedirect(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image", e);
        }
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