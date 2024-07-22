package org.kane.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.kane.dao.PermissionDao;
import org.kane.entities.Permission;
import org.kane.entities.Role;

import java.util.List;

public class PermissionDaoImpl implements PermissionDao {

    private final SessionFactory sessionFactory;

    public PermissionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Permission> findAllPermissions() {
        Transaction tx = null;
        try {
            Session session = getCurrentSession();

            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Permission.class);
            return criteria.list();
        }
        catch(Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }


}
