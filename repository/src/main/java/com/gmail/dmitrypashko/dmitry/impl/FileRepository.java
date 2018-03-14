package com.gmail.dmitrypashko.dmitry.impl;

import com.gmail.dmitrypashko.dmitry.IFileRepository;
import com.gmail.dmitrypashko.dmitry.model.FileEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepository extends GenericDao<FileEntity, Long> implements IFileRepository {
}
