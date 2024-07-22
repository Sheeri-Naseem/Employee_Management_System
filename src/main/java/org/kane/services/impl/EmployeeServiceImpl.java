package org.kane.services.impl;

import org.kane.dao.EmployeeDao;
import org.kane.dao.RoleDao;

import org.kane.entities.Employee;
import org.kane.entities.Role;

import org.kane.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Employee emp;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int empId) {
        System.out.println("empId inside getEmpById() method of EmployeeServiceImpl : " + empId);
        return employeeDao.findEmployeeById(empId);
    }


    @Override
    public void addEmp(Employee employee) {
        try {
            System.out.println("Object inside addEmp() method of EmployeeServiceImpl : \n" + employee);
            employeeDao.saveEmployee(employee);
            System.out.println("new employee saved ! ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try {
            System.out.println("Object inside updateEmp() method of EmployeeServiceImpl : \n" + employee);
            employeeDao.editEmployee(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployeeById(int empId) {
      try {
          System.out.println("Inside deleteEmp() of empservice:\n");
          employeeDao.deleteEmployeeById(empId);
          System.out.println("msg from emp service: employee deleted ! ");
      }
      catch(Exception e){
          e.printStackTrace();
      }

    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    public boolean hasEditPerm(Employee e, String edit) {
        System.out.println("emp inside hasEditPerm() method of EmployeeServiceImpl : " + e);
        return e.getRole().getPermissions().stream().anyMatch(permission -> permission.getPermission_type().equals(edit));
    }

    @Override
    public boolean hasDeletePerm(Employee e, String delete) {
        System.out.println("emp inside hasEditPerm() method of EmployeeServiceImpl : " + e);
        return e.getRole().getPermissions().stream().anyMatch(permission -> permission.getPermission_type().equals(delete));
    }

    @Override
    public void promoteToManager(int employeeId) {
        Role managerRole;
        List<Role> roles = roleDao.findAllRoles();
        for(Role role : roles){
            if(role.getRoleName().equalsIgnoreCase("manager")){
                managerRole = role;
                emp = getEmployeeById(employeeId);
                emp.setRole(managerRole);
break;
            }
        }
System.out.println(emp);
        employeeDao.editEmployee(emp);
    }


}
