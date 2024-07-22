package org.kane.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.kane.dao.PermissionDao;
import org.kane.dao.RoleDao;
import org.kane.entities.Permission;
import org.kane.entities.Role;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    private final SessionFactory sessionFactory;

    private PermissionDao permissionDao;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Role> findAllRoles() {
        Transaction tx = null;
       try {
           Session session = getCurrentSession();

           tx = session.beginTransaction();
           Criteria criteria = session.createCriteria(Role.class);
           List<Role> roles = criteria.list();
           tx.commit();
           return roles;
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
    public List<Permission> findPermissionsByRole(Role role) {
        List<Role> roles = findAllRoles();

        System.out.println("All roles inside findPermissionsByRole(role) of RoleDao: " + roles);

        for(Role r : roles){
            if(r.equals(role)) {
                System.out.println(r.getPermissions());
                return r.getPermissions();
            }
        }
        return List.of();
    }


}
