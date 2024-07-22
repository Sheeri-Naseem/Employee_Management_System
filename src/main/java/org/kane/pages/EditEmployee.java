package org.kane.pages;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.http.services.Response;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.util.AbstractSelectModel;
import org.kane.entities.Address;
import org.kane.entities.Employee;
import org.kane.entities.Role;
import org.kane.services.EmployeeService;

import javax.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EditEmployee {

    @Property
    private Employee employee;

    @PageActivationContext
    private int empId;

    @Property
    private Role role;

    @SessionState
    private Employee loggedInEmployee;

    @Property
    private Date dateOfBirth;

    @Property
    // We could return a DateFormat, but instead we'll return a String which DateField will coerce into a DateFormat.
    private String dobFormatStr = "dd/MM/yyyy";

    @Property
    @Validate("required")
    private String retypePassword;

    @InjectPage
    private EmployeeDetails employeeDetails;

    @InjectComponent("editEmployeeForm")
    private Form editEmployeeForm;

    @Inject
    private EmployeeService employeeService;

    @InjectPage
    private EmployeesList employeesList;

    @Inject
    private Request request;

    @Inject
    private Response response;


    void onActivate(int empId){
        System.out.println("onActivate() called.. \n");
        this.empId = empId;
        this.employee = employeeService.getEmployeeById(empId);

    }

    public void onPrepareForRender()throws Exception{

        if(editEmployeeForm.isValid()){
            if(employee==null){
                employee = new Employee();
            }
        }
    }


    public void onPrepareFromEditEmployeeForm(){
        System.out.println("\n onPrepareFromEditEmployeeForm() called.. \n");
        if (editEmployeeForm.getHasErrors()) {
            return;
        }
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
        System.out.println("inside editEmp.java - getRoles() : \n calling employeeService.getAllRoles() : \n ");
        List<Role> roles = employeeService.getAllRoles();
        System.out.println("fetched roles from DB  :  "+roles);
        return roles;
    }

    public ValueEncoder<Role> getEncoder(){
        System.out.println("inside editEmp.java - getRoleModel() : \n");

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


    public void onPrepareForSubmit() {
        System.out.println("onPrepareForSubmit() called.. \n");
        // Getting objects for the form fields to overlay.
        if (employee == null) {
            employee = new Employee();
        }
        System.out.println("current user : "+loggedInEmployee);
        System.out.println(employee);
    }


    public void onValidateFromEditEmployeeForm(){
        System.out.println("onValidateFromEditEmployeeForm() called.. \n");

        if (employee.getUname().isEmpty() || employee.getEmail().isEmpty() ||
                employee.getAddress().getStreet().isEmpty() || employee.getAddress().getCity().isEmpty() ||
                employee.getAddress().getState().isEmpty() || employee.getAddress().getPinCode().isEmpty() ||
                employee.getPhone().isEmpty() || employee.getRole().getRoleName().isEmpty() ||
                employee.getGender().isEmpty() || employee.getDateOfBirth().toString().isEmpty() ||
                employee.getPassword().isEmpty() || retypePassword.isEmpty()) {
            editEmployeeForm.recordError("All fields are required!");
        }

        if (!employee.getPassword().equals(retypePassword)) {
            editEmployeeForm.recordError("Password and Retype Password must match!");
        }
    }

    Object onSuccess() {
        System.out.println("onSuccess() called.. \n");

        System.out.println("Form submit successful! Entered data:");
        System.out.println(employee.getUname()+ " "+ employee.getEmail() +" " +employee.getGender()+ " " + employee.getAge() + " " +
                employee.getDateOfBirth()+ " " + employee.getAddress().getStreet()+ " " + employee.getAddress().getCity()+ " " +
                employee.getAddress().getState() + " " + employee.getAddress().getPinCode() + " " + employee.getPhone() + " " + employee.getPassword() );

        try {
            employeeService.updateEmployee(employee);
        } catch (Exception e) {
            editEmployeeForm.recordError("An error occurred while updating the employee.");
        }
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
