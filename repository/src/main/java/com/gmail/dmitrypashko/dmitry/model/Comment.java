package com.gmail.dmitrypashko.dmitry.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "T_COMMENT")
public class Comment implements Serializable {

    private static final long serialVersionUID = 3L;
    @Id
    @Column(name = "F_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "F_COMMENT")
    @Type(type = "text")
    @Size(min = 4,max = 10000)
    @NotNull
    private String comment;

    @ManyToOne
    @JoinColumn(name = "F_USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "F_NEWS_ID")
    private News news;

    public Comment() {
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
