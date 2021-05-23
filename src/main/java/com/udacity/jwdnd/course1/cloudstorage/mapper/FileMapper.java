package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface  FileMapper {

    @Insert("INSERT INTO files (filename, contenttype, filesize, fk_userid, filedata,instime) VALUES" +
                            "(#{filename}, #{contenttype}, #{filesize}, #{fk_userid}, #{filedata},#{instime})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Select("SELECT * FROM files where fk_userid=#{fk_userid}")
    List<File> getAllfiles(int fk_userid);

    @Select("SELECT * FROM files where fileId=#{fileId} ")
    File getfileById(int fileId);

    @Select("SELECT * FROM files where filename=#{filename} ")
    List<File> getfileByname(String filename);

    @Delete("delete from files where fileId=#{fileId}")
    void deleteById(int fileId);
}
