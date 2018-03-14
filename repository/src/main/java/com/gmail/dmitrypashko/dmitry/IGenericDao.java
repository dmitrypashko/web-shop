package com.gmail.dmitrypashko.dmitry;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable, ID extends Serializable> {


    void saveOrUpData(T entity);

    void save(T entity);

    void delete(T entity);

    T findById(ID id);

    List<T> getAll(int pagination);

    List<Integer> getPaginationList();

}
