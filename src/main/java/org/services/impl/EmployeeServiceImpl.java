package org.services.impl;

import org.entities.Employee;
import org.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = Arrays.asList(
                new Employee(1, "Ankit", 35, "Mishra", "Mumbai"),
                new Employee(2, "Aishwarya", 30, "Verma", "Gurgaon"),
                new Employee(3, "Sadaf", 25, "Khan", "Lucknow"),
                new Employee(4, "Urvashi", 29, "Singh", "Delhi"),
                new Employee(5, "Nausheen", 26, "Anwar", "Delhi"),
                new Employee(6,  "Nandini", 40, "Daksh", "Noida"),
                new Employee(7, "Avinash", 31, "Singh", "Jaipur")
        );

//    this.employeeList.add(new Employee(1, "Ankit", 35, "Mishra", "Mumbai"));
//        this.employeeList.add(new Employee(2, "Aishwarya", 30, "Verma", "Gurgaon"));
//                this.employeeList.add(new Employee(3, "Nandini", 40, "Daksh", "Noida"));
//                        this.employeeList.add(new Employee(4, "Urvashi", 25, "Singh", "Delhi"));
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

    @Override
    public void addEmp(int empId, String name, int age, String address, String password) {
        try {
            this.employeeList.add(new Employee(empId, name, age, address, password));
            System.out.println(employeeList);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
