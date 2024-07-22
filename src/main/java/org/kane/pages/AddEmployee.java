package org.kane.pages;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.http.services.Response;
import org.apache.tapestry5.internal.OptionModelImpl;

import org.apache.tapestry5.util.AbstractSelectModel;
import org.kane.entities.Address;
import org.kane.entities.Employee;
import org.kane.entities.Role;
import org.kane.services.EmployeeService;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

public class AddEmployee {

    @Property
    private Employee employee;

    @Property
    private Role role;

    @Property
    private Date dateOfBirth;

    @Property
    // We could return a DateFormat, but instead we'll return a String which DateField will coerce into a DateFormat.
    private String dobFormatStr = "dd/MM/yyyy";

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

    @SessionState
    private Employee loggedInEmployee;

    @javax.inject.Inject
    private Request request;

    @javax.inject.Inject
    private Response response;

    public ValueEncoder<Role> getEncoder(){
        System.out.println("inside ValueEncoder() : \n");

        return new ValueEncoder<Role>() {
            @Override
            public String toClient(Role role) {
                return String.valueOf(role.getRoleId());
            }

            @Override
            public Role toValue(String id) {
        //use parseint for id and return the selected role

                for(Role availableRole : getRoles()){
                    if(Integer.parseInt(id) == availableRole.getRoleId())
                        return availableRole;
                }
                return null;
            }
        };
    }


    public SelectModel getRoleModel(){
System.out.println("inside getRoleModel() : \n");

        List<Role> roles = getRoles();
        if (roles == null) {
            roles = List.of(); // Return an empty list if roles is null
        }
        List<Role> finalRoles = roles;
        return new AbstractSelectModel(){

            @Override
            public List<OptionGroupModel> getOptionGroups() {
                return null;
            }

            @Override
            public List<OptionModel> getOptions() {
                return finalRoles
                        .stream()
                        .map(role -> new OptionModelImpl(role.getRoleName() , role))
                        .collect(Collectors.toUnmodifiableList());
            }
        };
    }

    @Cached
    public List<Role> getRoles() {
        System.out.println("inside getRoles() : \n now calling employeeService.getAllRoles() : \n ");
        List<Role> roles = employeeService.getAllRoles();
        return roles;
}

    public void onPrepareFromAddEmployeeForm(){
        System.out.println("inside OnPrepare() : ");

        if(employee==null){
            employee = new Employee();
        }
         System.out.println(employee);
        if(employee.getAddress()==null){
            employee.setAddress(new Address());
        }
        System.out.println(employee);

    }

    void onValidateFromAddEmployeeForm() {
        System.out.println("inside onValidate() : ");

        employee.setPassword(password);
        if (employee.getUname().isEmpty() || employee.getAge() == 0  ||  employee.getEmail().isEmpty() ||
                employee.getAddress().getStreet().isEmpty() || employee.getAddress().getCity().isEmpty() ||
                employee.getAddress().getState().isEmpty() || employee.getAddress().getPinCode().isEmpty() ||
                employee.getPhone().isEmpty() || employee.getRole().getRoleName().isEmpty() ||
                employee.getGender().isEmpty() || employee.getDateOfBirth().toString().isEmpty() ||
                employee.getPassword().isEmpty() || retypePassword.isEmpty()) {
            addEmployeeForm.recordError("All fields are required!");
        }

        if (employee.getAge() <= 0) {
            addEmployeeForm.recordError("Age must be a positive number!");
        }

        if (!employee.getPassword().equals(retypePassword)) {
            addEmployeeForm.recordError("Password and Retype Password must match!");
        }
    }

    Object onSuccess() {
        System.out.println("inside onSuccess() : ");

        employee.setPassword(password);

        System.out.println("Form submit successful! Entered data:");
        System.out.println(employee.getUname()+ " "+ employee.getEmail() +" " +employee.getGender()+ " " + employee.getAge() + " " +
                employee.getDateOfBirth()+ " " + employee.getAddress().getStreet()+ " " + employee.getAddress().getCity()+ " " +
                employee.getAddress().getState() + " " + employee.getAddress().getPinCode() + " " + employee.getPhone() + " " + employee.getPassword() );

        //call addEmp() service method
        employeeService.addEmp(employee);

        //return to emp list page
        return employeesList;
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
