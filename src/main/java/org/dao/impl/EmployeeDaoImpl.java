package org.dao.impl;

import org.dao.EmployeeDao;
import org.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public Employee findById(int empId) {

        return getCurrentSession().get(Employee.class, empId);
    }

    @Override
    public void save(Employee employee) {

        getCurrentSession().saveOrUpdate(employee);

        System.out.println(employee + " added ! ");
    }

    @Override
    public void deleteById(int empId) {

        Employee employee = findById(empId);
        getCurrentSession().delete(employee);
        System.out.println(employee + " deleted ! ");
    }
}
