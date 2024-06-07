package org.services.impl;

import org.entities.Employee;
import org.services.EmployeeService;
import org.services.LoginService;

import javax.inject.Inject;
import java.util.List;

public class LoginServiceImpl implements LoginService {

    @Inject
    private EmployeeService employeeService;

    @Override
    public boolean isValid(String username, String password) {

        List<Employee> empList = employeeService.getAllEmployees();

        return empList
                .stream()
                .anyMatch(emp->emp.getName().equals(username) && emp.getPassword().equals(password));
    }
}
