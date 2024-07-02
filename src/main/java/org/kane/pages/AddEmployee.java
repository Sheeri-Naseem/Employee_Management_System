package org.kane.pages;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.services.StringValueEncoder;
import org.apache.tapestry5.util.AbstractSelectModel;
import org.kane.entities.Address;
import org.kane.entities.Employee;
import org.kane.entities.Role;
import org.kane.services.EmployeeService;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddEmployee {

    @Property
    private Employee employee;

    @Property
    private Role role;

//    @Property
//    private Address address;

//    @Property
//    private List<Role> availableRoles;

//    @Property
//    private List<Role> selectedRoles ;


// @Property
//    @Validate("required")
//    private int empid;

//    @Property
//    @Validate("required")
//    private String uname;
//
//    @Property
//    @Validate("required")
//    private String email;
//
//    @Property
//    @Validate("required")
//    private int age;
//
////    address
//    @Property
//    @Validate("required")
//    private String street;
//
//    @Property
//    @Validate("required")
//    private String city;
//
//    @Property
//    @Validate("required")
//    private String state;
//
//    @Property
//    @Validate("required")
//    private String pinCode;
//
//    @Property
//    @Validate("required")
//    private String phone;

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

    public ValueEncoder<Role> getEncoder(){
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

//                return getRoles().stream()
//                        .filter(role -> Integer.parseInt(id) == role.getRoleId())
//                        .findFirst()
//                        .orElse(null);
            }
        };
    }


    public SelectModel getRoleModel(){
        return new AbstractSelectModel(){

            @Override
            public List<OptionGroupModel> getOptionGroups() {
                return null;
            }

            @Override
            public List<OptionModel> getOptions() {
                return getRoles()
                        .stream()
                        .map(role -> new OptionModelImpl(role.getRoleName() , role))
                        .collect(Collectors.toUnmodifiableList());
            }
        };
    }

//    void setupRender() {
//        employee = new Employee();
//        address = new Address();
//        employee.setAddress(address);
//        availableRoles = getRoles();
//        selectedRoles = new ArrayList<>();

        //initializing all the roles as unselected
//        for(int i=0; i < availableRoles.size(); i++){
//            selectedRoles.add(false);
//        }

//    }

    private List<Role> getRoles() {
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Role(1,"Manager"));
//        roles.add(new Role(2, "Junior Developer"));
//        roles.add(new Role(3, "Senior Developer"));
//        roles.add(new Role(4, "Staff Engineer"));
//        roles.add(new Role(5, "Software Consultant"));
//        roles.add(new Role(6,  "Trainee"));
//        roles.add(new Role(7,"HR"));
//    return roles;

        List<Role> roles = employeeService.getAllRoles();
        System.out.println("fetched roles from DB : "+roles);
        return roles;
}

    public void onPrepareFromAddEmployeeForm(){
        if(employee==null){
            employee = new Employee();
        }

        if(employee.getAddress()==null){
            employee.setAddress(new Address());
        }
    }

    void onValidateFromAddEmployeeForm() {
        employee.setPassword(password);
        if (employee.getUname().isEmpty() || employee.getAge() == 0  ||  employee.getEmail().isEmpty() ||
                employee.getAddress().getStreet().isEmpty() || employee.getAddress().getCity().isEmpty() ||
                employee.getAddress().getState().isEmpty() || employee.getAddress().getPinCode().isEmpty() ||
                employee.getPhone().isEmpty() || employee.getPassword().isEmpty() || retypePassword.isEmpty()) {
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
        employee.setPassword(password);

        System.out.println("Form submit successful! Entered data:");
        System.out.println(employee.getUname()+ " "+ employee.getEmail() +" " + employee.getAge() + " " + employee.getAddress().getStreet()
                + " " + employee.getAddress().getCity()+ " " + employee.getAddress().getState() + " " +
                employee.getAddress().getPinCode() + " " + employee.getPhone() + " " + employee.getPassword());

//        address = new Address(street, city, state, pinCode,employee);
//        employee = new Employee(uname, age, address, password);



//        for (Map.Entry<Integer, Boolean> entry : selectedRoles.entrySet()) {
//            if (entry.getValue()) {
//                roles.add(availableRoles.get(entry.getKey()));
//            }
//        }

//        employee.setAddress(address);

//        List<Role> roles = new ArrayList<>();

//        for(int i=0; i<selectedRoles.size(); i++){
//            if(selectedRoles.get(i)){
//                roles.add(availableRoles.get(i));
//            }
//        }

//        employee.setRoles(roles);

        //call addEmp() service method
        employeeService.addEmp(employee);

        //return to emp list page
        return employeesList;
    }

}
