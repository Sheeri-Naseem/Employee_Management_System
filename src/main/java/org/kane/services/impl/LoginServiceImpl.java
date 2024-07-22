package org.kane.services.impl;

import org.kane.entities.Employee;
import org.kane.services.EmployeeService;
import org.kane.services.LoginService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Inject
    private EmployeeService employeeService;

    private List<Employee> empList ;

    @Override
    public boolean isValid(String username, String password) {
        empList = employeeService.getAllEmployees();
        return empList
                .stream()
                .anyMatch(emp->emp.getUname().equals(username) && emp.getPassword().equals(password));
    }

    @Override
    public Employee findByUsernameAndPassword(String username, String password) {
        empList = employeeService.getAllEmployees();
        for(Employee emp : empList){
            if(emp.getUname().equals(username) && emp.getPassword().equals(password)){
                return emp;
            }
        }
        return null;
    }


}
