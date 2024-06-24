package org.dao;

import org.entities.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();
    Employee findById(int empId);
    void save(Employee employee);
    void deleteById(int empId);
}
