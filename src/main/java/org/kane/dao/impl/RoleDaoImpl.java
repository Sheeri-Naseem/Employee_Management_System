//package org.kane.dao.impl;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.kane.dao.RoleDao;
//import org.kane.entities.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//public class RoleDaoImpl implements RoleDao {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Transaction tx ;
//
//    private Session session;
//
//    private Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    @Override
//    public List<Role> findAllRoles() {
//       try {
//           session = getCurrentSession();
//           if(!session.isOpen()){
//               session=sessionFactory.openSession();
//           }
//           tx = session.beginTransaction();
//           Criteria criteria = session.createCriteria(Role.class);
//           List<Role> roles = criteria.list();
//           tx.commit();
//           return roles;
//       }
//        catch(Exception e){
//           e.printStackTrace();
//           tx.rollback();
//
//       }
//       return null;
//    }
//}
