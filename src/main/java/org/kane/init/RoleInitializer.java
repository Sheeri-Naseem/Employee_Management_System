//package org.kane.init;
//
//import org.kane.dao.RoleDao;
//import org.kane.entities.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Arrays;
//
//@Component
//public class RoleInitializer {
//
//    @Autowired
//    private RoleDao roleDao;
//
//    @PostConstruct
//    public void init(){
//        Role[] roles = {
//                new Role(1, "Manager"),
//                new Role(2, "Junior Developer"),
//                new Role(3, "Senior Developer"),
//                new Role(4, "Staff Engineer"),
//                new Role(5, "Software Consultant"),
//                new Role(6, "Trainee"),
//                new Role(7, "HR")
//        };
//        Arrays.stream(roles).forEach(role-> roleDao.save(role));
//    }
//
//}
