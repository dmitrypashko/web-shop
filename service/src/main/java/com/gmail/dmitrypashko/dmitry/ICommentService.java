package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.Comment;
import com.gmail.dmitrypashko.dmitry.model.User;

import java.util.List;

public interface ICommentService {

    void saveComment(Long idNews, User user, Comment comment);

    List<Comment> getCommentByNews(Long idNews);

    void deleteComment(Long idComment);
}
