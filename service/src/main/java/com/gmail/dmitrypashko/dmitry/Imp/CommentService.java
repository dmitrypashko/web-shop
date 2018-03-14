package com.gmail.dmitrypashko.dmitry.Imp;

import com.gmail.dmitrypashko.dmitry.model.Comment;
import com.gmail.dmitrypashko.dmitry.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.dmitrypashko.dmitry.ICommentService;
import com.gmail.dmitrypashko.dmitry.ICommentRepository;
import com.gmail.dmitrypashko.dmitry.INewsRepository;
import com.gmail.dmitrypashko.dmitry.model.User;

import java.util.List;
@Service
public class CommentService implements ICommentService {

    @Autowired
    private INewsRepository newsDao;
    @Autowired
    private ICommentRepository commentDao;
    @Transactional
    @Override
    public void saveComment(Long idNews, User user, Comment comment) {
        News news = newsDao.findById(idNews);
        if (news != null){
            comment.setNews(news);
            comment.setUser(user);
            commentDao.save(comment);
        }
    }

    @Transactional
    @Override
    public List<Comment> getCommentByNews(Long idNews) {
        return commentDao.getCommentByNews(idNews);
    }
    @Transactional
    @Override
    public void deleteComment(Long idComment) {
        Comment comment = commentDao.findById(idComment);
        if (comment != null){
            commentDao.delete(comment);
        }
    }

}
