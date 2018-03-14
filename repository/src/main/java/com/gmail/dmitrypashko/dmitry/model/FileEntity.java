package com.gmail.dmitrypashko.dmitry.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "T_FILE_NEWS")
public class FileEntity implements Serializable {

    private static final long serialVersionUID = 2348765L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_NEWS_ID",nullable = false)
    private Long newsId;
    @Column(name = "F_FILE_NAME")
    private String fileName;
    @Column(name = "F_LOCATION")
    private String location;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "F_NEWS_ID")
    private News news;

    public FileEntity() {
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
