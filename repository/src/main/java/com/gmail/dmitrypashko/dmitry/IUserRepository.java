package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.User;

public interface IUserRepository extends IGenericDao<User, Long> {
    User getUser(String email);

    void changeRoleUser(Long id, String role);

    void changeStatusUser(Long id, String status);

}
