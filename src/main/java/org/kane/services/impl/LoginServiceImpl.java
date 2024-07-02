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

    @Override
    public boolean isValid(String username, String password) {

        List<Employee> empList = employeeService.getAllEmployees();

        return empList
                .stream()
                .anyMatch(emp->emp.getUname().equals(username) && emp.getPassword().equals(password));
    }
}
