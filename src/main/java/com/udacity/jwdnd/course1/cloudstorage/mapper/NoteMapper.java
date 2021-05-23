package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO notes (notetitle, notedescription, fk_userid) VALUES" +
                              "(#{notetitle}, #{notedescription}, #{fk_userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNote(Notes note);

    @Select("SELECT * FROM notes where fk_userid=#{fk_userid}")
    List<Notes> getAllNotes(int fk_userid);

    @Select("SELECT * FROM notes where noteid=#{noteid}")
    Notes getNotesById(int noteid);

    @Delete("delete from notes where noteid=#{noteid}")
    void deleteById(int noteid);

    @Update("update  notes set  notetitle=#{notetitle}, notedescription=#{notedescription},fk_userid=#{fk_userid} where noteid=#{noteid}")
    //int updateNote(String  notetitle,String notedescription,int fk_userid,int noteid);
    int updateNote(Notes notes);
}
