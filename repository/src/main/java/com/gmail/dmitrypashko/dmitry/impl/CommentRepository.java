package com.gmail.dmitrypashko.dmitry.impl;


import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.gmail.dmitrypashko.dmitry.ICommentRepository;
import com.gmail.dmitrypashko.dmitry.model.Comment;

import java.util.List;

@Repository
public class CommentRepository extends GenericDao<Comment, Long> implements ICommentRepository {

    @Override
    public List<Comment> getCommentByNews(Long idNews) {
        Query query = getSession().createQuery("FROM Comment where news.id =:idNews");
        query.setParameter("idNews", idNews);
        return (List<Comment>) query.list();
    }
}
