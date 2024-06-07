package org.services.impl;

import org.entities.Employee;
import org.services.EmployeeService;

import java.util.Arrays;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = Arrays.asList(
                new Employee(1, "Ankit", 35, "Mishra", "Mumbai"),
                new Employee(2, "Aishwarya", 30, "Verma", "Gurgaon"),
                new Employee(3, "Nandini", 40, "Daksh", "Noida"),
                new Employee(4, "Urvashi", 25, "Singh", "Delhi")

        );
    }

    @Override
    public Employee getEmployeeById(int empId) {
        for(Employee employee : employeeList){
            if(employee.getEmpId()==empId){
                return employee;
            }
        }
        return null;
    }

    @Override
    public Employee getEmployeeByUserName(String username) {
        for(Employee employee : employeeList){
            if(employee.getName().equals(username)){
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }
}
