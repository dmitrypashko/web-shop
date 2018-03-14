package com.gmail.dmitrypashko.dmitry.Imp;

import com.gmail.dmitrypashko.dmitry.IFileRepository;
import com.gmail.dmitrypashko.dmitry.IFileService;
import com.gmail.dmitrypashko.dmitry.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class FileService implements IFileService {

    @Autowired
    private IFileRepository fileDao;

    @Override
    @Transactional
    public File getFileById(Long id) {
        FileEntity fileEntity = fileDao.findById(id);
        String nameFile = fileEntity.getFileName();
        String locationFile = fileEntity.getLocation();
        return new File(locationFile + nameFile);
    }
}
