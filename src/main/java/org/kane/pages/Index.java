package org.kane.pages;

import org.kane.entities.Employee;
import org.kane.services.LoginService;
import org.apache.tapestry5.alerts.AlertManager;
//import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
//import components.kane.Header;
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


//    @Component
//    private Header header;


    void onValidateFromLoginForm(){

        if(!loginService.isValid(username,password)){
            alertManager.alert(Duration.UNTIL_DISMISSED, Severity.ERROR,"Invalid username or password! Try Again.");
            loginForm.recordError("Invalid username or password !");
        }
    }

    Object onSuccess(){
        System.out.println("Login Successful");
        alertManager.success("Welcome back "+username + " !");
        return employeesList;
    }



}
