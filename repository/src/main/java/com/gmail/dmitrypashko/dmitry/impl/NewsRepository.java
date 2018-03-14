package com.gmail.dmitrypashko.dmitry.impl;

import com.gmail.dmitrypashko.dmitry.model.News;
import org.springframework.stereotype.Repository;
import com.gmail.dmitrypashko.dmitry.INewsRepository;


@Repository
public class NewsRepository extends GenericDao<News, Long> implements INewsRepository {

}
