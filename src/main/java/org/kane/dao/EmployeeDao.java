package org.kane.dao;

import org.kane.entities.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAllEmployees();
    Employee findEmployeeById(int empId);
    void saveEmployee(Employee employee);
    void editEmployee(Employee employee);
    void deleteEmployeeById(int empId);
}
