package org.kane.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.kane.dao.EmployeeDao;
import org.kane.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

    private Employee employee;

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Employee> findAllEmployees() {
        Transaction tx = null;
        try {
            Session session = getCurrentSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            List<Employee> list = criteria.list();
            tx.commit();
            return list;
        }
        catch(Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee findEmployeeById(int empId) {
        Transaction tx = null;
        try {
            Session session = getCurrentSession();
            tx = session.beginTransaction();
            employee = session.get(Employee.class, empId);
            tx.commit();
            return  employee;
        }
        catch(Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void saveEmployee(Employee employee) {
        Transaction tx = null;
       try {
           Session session = getCurrentSession();
           tx = session.beginTransaction();
           session.save(employee);
           tx.commit();
           System.out.println(employee + " added ! ");
       }
       catch(Exception e){
           if(tx!=null){
               tx.rollback();
           }
          e.printStackTrace();
       }
    }

    @Override
    public void editEmployee(Employee employee) {
        System.out.println("emp Obj inside editEmp() of EmpDaoImpl : "+employee);
                Transaction tx = null;
        try {
            Session session = getCurrentSession();
            tx = session.beginTransaction();
            if(employee!=null) {
                session.update(employee);
            }
            tx.commit();
            System.out.println(employee + "details updated ! ");

        }
        catch(Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployeeById(int empId) {
        Transaction tx = null;
        try {
            Session session = getCurrentSession();
        tx = session.beginTransaction();
        employee = session.get(Employee.class, empId);
            System.out.println("Inside deleteById() of empDao empId = "+empId +" , fetched emp : "+employee);
        if(employee!=null) {
            session.delete(employee);
        }
        System.out.println(employee + " deleted ! ");
        tx.commit();
        }
        catch(Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
