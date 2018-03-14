package com.gmail.dmitrypashko.dmitry.Imp;

import com.gmail.dmitrypashko.dmitry.model.News;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.gmail.dmitrypashko.dmitry.INewsService;
import com.gmail.dmitrypashko.dmitry.INewsRepository;
import com.gmail.dmitrypashko.dmitry.model.FileEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class NewsService implements INewsService {
    @Autowired
    private INewsRepository newsDao;

    private static final Logger logger = Logger.getLogger(NewsService.class);

    @Transactional
    @Override
    public void addNews(News news, MultipartFile image) {
        final String name = image.getOriginalFilename();
        final String location = "resources//images//news//";
        FileEntity fileEntity = new FileEntity();
        try {
            if (image.getSize() > 0 && image.getInputStream() != null && name != null) {
                final Path outputFile = Paths.get(location, name);
                fileEntity.setLocation(location);
                fileEntity.setFileName(name);
                news.setFileEntity(fileEntity);
                fileEntity.setNews(news);
                try (final ReadableByteChannel input = Channels.newChannel(image.getInputStream());
                     final WritableByteChannel output = Channels.newChannel(new FileOutputStream(outputFile.toFile()))) {
                    final ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (input.read(buffer) >= 0 || buffer.position() > 0) {
                        buffer.flip();
                        output.write(buffer);
                        buffer.compact();
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        newsDao.saveOrUpData(news);
    }

    @Override
    @Transactional
    public List<Integer> getPaginationList() {
       return newsDao.getPaginationList();
    }

    @Transactional
    @Override
    public List<News> getAllNews(int pagination) {
        return newsDao.getAll(pagination);

    }

    @Transactional
    @Override
    public News getNews(Long idNews) {
        News news = newsDao.findById(idNews);
        if (news != null){
            return newsDao.findById(idNews);
        }
        return  null;
    }

    @Transactional
    @Override
    public void deleteNews(Long idNews) {
        News news = newsDao.findById(idNews);
        if (news != null){
            newsDao.delete(news);
        }
    }
    @Transactional
    @Override
    public void changeNews(News news,MultipartFile image) {
        final String name = image.getOriginalFilename();
        final String location = "E://pictures//";
        FileEntity fileEntity = new FileEntity();
        try {
            if (image.getSize() > 0 && image.getInputStream() != null && name != null) {
                fileEntity.setNewsId(news.getId());
                fileEntity.setLocation(location);
                fileEntity.setFileName(name);
                news.setFileEntity(fileEntity);
                fileEntity.setNews(news);
                final Path outputFile = Paths.get(location, name);
                try (final ReadableByteChannel input = Channels.newChannel(image.getInputStream());
                     final WritableByteChannel output = Channels.newChannel(new FileOutputStream(outputFile.toFile()))) {
                    final ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (input.read(buffer) >= 0 || buffer.position() > 0) {
                        buffer.flip();
                        output.write(buffer);
                        buffer.compact();
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        newsDao.saveOrUpData(news);
    }



}
