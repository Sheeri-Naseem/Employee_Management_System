package org.kane.pages;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.http.services.Response;
import org.hibernate.Session;
import org.kane.components.Header;
import org.kane.entities.Employee;
import org.kane.services.LoginService;
import org.apache.tapestry5.alerts.AlertManager;
//import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.annotations.Inject;


public class Index {

    @Property
    Employee employee;

    @Property
    @Validate("required")
    private String username;

    @Property
    @Validate("required")
    private String password;

    @Inject
    private AlertManager alertManager;

    @InjectComponent("password")
    private PasswordField passwordField;

    @InjectComponent
    private Form loginForm;

    @Inject
    private LoginService loginService;

    @InjectPage
    private EmployeesList employeesList;

    @SessionState
    private Employee loggedInEmployee;

    @Inject
    private Request request;

    @Inject
    private Response response;

    void onValidateFromLoginForm(){

        if(!loginService.isValid(username,password)){
            alertManager.alert(Duration.UNTIL_DISMISSED, Severity.ERROR,"Invalid username or password! Try Again.");
            loginForm.recordError("Invalid username or password !");
        }
    }

    void onSuccess(){
        System.out.println("Login Successful");
        Employee employee = loginService.findByUsernameAndPassword(username,password);
        if(employee!=null){

            loggedInEmployee = employee;
            System.out.println("LOGGED IN USER SET AS : "+loggedInEmployee);
            try {
                response.sendRedirect(request.getContextPath() + "/employeeslist");
            } catch (Exception e) {
                throw new RuntimeException("Redirection failed");
            }
            alertManager.success("Welcome back "+username + " !");
        }

        }
}
