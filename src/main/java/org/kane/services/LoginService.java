package org.kane.services;

import org.kane.entities.Employee;

public interface LoginService {
    boolean isValid(String username, String password);

    Employee findByUsernameAndPassword(String username, String password);

}
