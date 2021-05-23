package com.udacity.jwdnd.course1.cloudstorage.storage;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    int store(MultipartFile file,int userid);

    List<File> loadAllFile(Authentication authentication);


    Stream<Path> loadAll();

    Path load(String filename);

    File loadSingleFile(int fileid);

    boolean checkFileExistanse(String filename);

    void deleteAll();

    void deleteById(int fileId);

}
