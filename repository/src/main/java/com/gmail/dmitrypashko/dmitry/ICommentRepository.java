package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.Comment;

import java.util.List;

public interface ICommentRepository extends IGenericDao<Comment, Long> {
    List<Comment> getCommentByNews(Long idNews);
}
