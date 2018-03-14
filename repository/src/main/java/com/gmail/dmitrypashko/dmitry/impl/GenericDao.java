package com.gmail.dmitrypashko.dmitry.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.gmail.dmitrypashko.dmitry.IGenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class GenericDao<T extends Serializable, ID extends Serializable> implements IGenericDao<T, ID> {

    private final Class<T> entityClass;

    public GenericDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Autowired
    @Qualifier(value = "factory")
    SessionFactory sessionFactory;


    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    public void saveOrUpData(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public T findById(ID id) {
        return getSession().get(this.entityClass, id);
    }

    public List<Integer> getPaginationList() {
        Query query = getSession().createQuery("FROM " + this.entityClass.getName());
        List<Integer> paginationList = new ArrayList<>();
        for (int i = 0; i <= (int) Math.ceil(query.list().size() / 5); i++) {
            paginationList.add(i + 1);
        }
        return paginationList;
    }

    public List<T> getAll(int pagination) {
        Query query = getSession().createQuery("FROM " + this.entityClass.getName());
        query.setFirstResult(5 * pagination);
        query.setMaxResults(5);
        return (List<T>) query.list();
    }
}
