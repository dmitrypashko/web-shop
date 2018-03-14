package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface INewsService {

    void addNews(News news, MultipartFile image);

    List<News> getAllNews(int pagination);

    News getNews(Long idNews);

    void deleteNews(Long idNews);

    void changeNews(News news, MultipartFile image);

    List<Integer> getPaginationList();
}
