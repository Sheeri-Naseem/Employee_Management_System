package org.services;

import org.entities.Employee;

import java.util.List;

public interface EmployeeService {
     Employee getEmployeeById(int empId);

     Employee getEmployeeByUserName(String username);

     List<Employee> getAllEmployees();

     void addEmp(int empId, String name, int age, String address, String password);
}
