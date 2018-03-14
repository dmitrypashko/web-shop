package com.gmail.dmitrypashko.dmitry.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_NEWS")
public class News implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @Column(name = "F_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "F_DATE")
    private String date;
    @Column(name = "F_HEADER")
    private String header;
    @Column(name = "F_CONTENT")
    @Type(type = "text")
    private String content;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "news")
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "news")
    private FileEntity fileEntity;


    public News() {
    }

    public FileEntity getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
