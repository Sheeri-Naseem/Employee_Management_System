package org.kane.services;

import org.kane.entities.Employee;
import org.kane.entities.Role;

import java.util.List;

public interface EmployeeService {
     Employee getEmployeeById(int empId);

//     Employee getEmployeeByUserName(String username);

     List<Employee> getAllEmployees();

     void addEmp(Employee employee);

    void deleteEmployeeById(int empId) ;

    List<Role> getAllRoles();
}
