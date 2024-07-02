package org.kane.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.kane.dao.EmployeeDao;
import org.kane.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Transaction tx ;

    private Session session;
    private Employee employee;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Employee> findAllEmployees() {
        session = getCurrentSession();
        tx = session.beginTransaction();
        Criteria criteria = getCurrentSession().createCriteria(Employee.class);
        List<Employee> list = criteria.list();
        tx.commit();
        return list;
    }

    @Override
    public Employee findEmployeeById(int empId) {
        session = getCurrentSession();
        tx = session.beginTransaction();
        employee = getCurrentSession().get(Employee.class, empId);
        tx.commit();;
        return  employee;
    }

    @Override
    public void saveEmployee(Employee employee) {
       try {
           session = getCurrentSession();
           tx = session.beginTransaction();
           session.saveOrUpdate(employee);
           System.out.println(employee + " added ! ");
           tx.commit();
       }
       catch(Exception e){
           tx.rollback();
          e.printStackTrace();
       }
    }

    @Override
    public void deleteEmployeeById(int empId) {
        session = getCurrentSession();
        tx = session.beginTransaction();
        employee = findEmployeeById(empId);
        if(employee!=null) {
            session.delete(employee);
        }
        System.out.println(employee + " deleted ! ");
        tx.commit();
    }
}
