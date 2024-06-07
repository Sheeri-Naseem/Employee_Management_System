package org.pages;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
//import org.components.Header;
import org.entities.Employee;
import org.services.LoginService;

import javax.inject.Inject;


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

    @Component
    private Form loginForm;

    @Inject
    private LoginService loginService;

//    @Component
//    private Header header;


    void onValidateFromLoginForm(){

        if(!loginService.isValid(username,password)){
            loginForm.recordError(passwordField,"Invalid username or password !");
        }
    }

    Object onSuccess(){
        System.out.println("Login Successful");
        alertManager.success("Welcome back "+username + " !");
        return EmployeesList.class;
    }



}
