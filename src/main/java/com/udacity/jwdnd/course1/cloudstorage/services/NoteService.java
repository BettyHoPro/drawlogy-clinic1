package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;
    private UserService userService;


    public NoteService(NoteMapper noteMapper,UserService userService) {

        this.noteMapper=noteMapper;
        this.userService=userService;
    }

    public void insNewNote(Notes notes, int user_id)
    {
        notes.setFk_userid(user_id);
        noteMapper.insertNote(notes);
    }

    public List<Notes> loadAllNotes (Authentication authentication)
    {
        return noteMapper.getAllNotes( userService.getUser(authentication.getName()).getUserId());
    }

    public Notes loadNoteById(int noteid)
    {
        return noteMapper.getNotesById(noteid);
    }
    public void delNote(int noteid)
    {
        noteMapper.deleteById(noteid);
    }

    public void updateNote(Notes note, int fkuser_id,int noteid)
    {
        note.setFk_userid(fkuser_id);
        note.setNoteid(noteid);
       // System.out.println(noteMapper.updateNote(note.getNotetitle(),note.getNotedescription(),note.getFk_userid(),note.getNoteid()));
        System.out.println(noteMapper.updateNote(note));
    }
}
