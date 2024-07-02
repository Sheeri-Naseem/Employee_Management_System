package org.kane.services.impl;

import org.kane.dao.EmployeeDao;
//import org.kane.dao.RoleDao;
import org.kane.dao.impl.EmployeeDaoImpl;
import org.kane.entities.Employee;
import org.kane.entities.Role;
import org.kane.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

//    private List<Employee> employeeList;

    private Employee emp;

    @Autowired
    private EmployeeDaoImpl employeeDao;

//    @Autowired
//    private RoleDao roleDao;

    @Override
    public List<Employee> getAllEmployees() {
//        if (employeeDao != null) {
            return employeeDao.findAllEmployees();
//        }
//        return List.of();
    }

    @Override
    public Employee getEmployeeById(int empId) {
//        for(Employee employee : employeeList){
//            if(employee.getEmpId()==empId){
//                return employee;
//            }
//        }
        return employeeDao.findEmployeeById(empId);

    }


    @Override
    public void addEmp(Employee employee) {
        try {
//            this.employeeList.add(new Employee(empId, name, age, address, password));
            System.out.println("Object inside addEmp() method of EmployeeServiceImpl : \n"+employee);
            employeeDao.saveEmployee(employee);
            System.out.println("new employee saved ! ");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

//        employeeDao.saveEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(int empId) {
            employeeDao.deleteEmployeeById(empId);

    }

//    @Override
//    public List<Role> getAllRoles() {
//        return roleDao.findAllRoles();
//    }
}


//    @Override
//    public Employee getEmployeeByUserName(String username) {
//        for(Employee employee : employeeList){
//            if(employee.getName().equals(username)){
//                return employee;
//            }
//        }
//        return null;
//    }
