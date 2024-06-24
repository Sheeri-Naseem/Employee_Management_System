package org.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.services.EmployeeService;

public class AddEmployee {

    @Property
    @Validate("required")
    private int empid;

    @Property
    @Validate("required")
    private String name;

    @Property
    @Validate("required")
    private int age;

    @Property
    @Validate("required")
    private String address;

    @Property
    @Validate("required")
    private String password;

    @Property
    @Validate("required")
    private String retypePassword;

    @InjectComponent("addEmployeeForm")
    private Form addEmployeeForm;

    @Inject
    private EmployeeService employeeService;

    @InjectPage
    private EmployeesList employeesList;

    void onValidateFromAddEmployeeForm() {

        if (empid == 0 || name.isEmpty() || age == 0  ||  address.isEmpty() || password.isEmpty() || retypePassword.isEmpty()) {
            addEmployeeForm.recordError("All fields are required!");
        }

        if (age <= 0) {
            addEmployeeForm.recordError("Age must be a positive number!");
        }

        if (!password.equals(retypePassword)) {
            addEmployeeForm.recordError("Password and Retype Password must match!");
        }
    }

    Object onSuccess() {
        System.out.println(empid + " " + name + " " + age + " " + address + " " + " " + password+ " "+retypePassword);
        System.out.println("Successfully added");

        //call add employee service method
//        employeeService.addEmp(empid, name, age, address, password);

        //return to emp list page
        return employeesList;
    }

}
