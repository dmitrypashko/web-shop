package com.gmail.dmitrypashko.dmitry.impl;

import com.gmail.dmitrypashko.dmitry.model.Role;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.dmitrypashko.dmitry.IUserRepository;
import com.gmail.dmitrypashko.dmitry.model.StatusUser;
import com.gmail.dmitrypashko.dmitry.model.User;

@Repository
public class UserRepository extends GenericDao<User, Long> implements IUserRepository {

    @Transactional
    @Override
    public User getUser(String email) {
        String queryString = "FROM User as U where U.email=:email";
        Query query = getSession().createQuery(queryString);
        query.setParameter("email", email);
        Object uniqueResult = query.uniqueResult();
        return uniqueResult != null ? (User) uniqueResult : null;
    }

    @Override
    public void changeRoleUser(Long id, String role) {
        Query query = getSession().createQuery("update User set role=:role where id=:id");
        query.setParameter("id", id);
        query.setParameter("role", Role.valueOf(role));
        query.executeUpdate();
    }

    @Override
    public void changeStatusUser(Long id, String status) {
        Query query = getSession().createQuery("update User set statusUser=:status where id=:id");
        query.setParameter("id", id);
        query.setParameter("status", StatusUser.valueOf(status));
        query.executeUpdate();
    }


}
