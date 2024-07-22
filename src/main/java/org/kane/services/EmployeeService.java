package org.kane.services;

import org.kane.entities.Employee;
import org.kane.entities.Role;

import java.util.List;

public interface EmployeeService {
     Employee getEmployeeById(int empId);

     List<Employee> getAllEmployees();

     void addEmp(Employee employee);

     void updateEmployee(Employee employee);

    void deleteEmployeeById(int empId) ;

    List<Role> getAllRoles();

    boolean hasEditPerm(Employee e, String edit);

    boolean hasDeletePerm(Employee e, String delete);

    void promoteToManager(int employeeId);
}
