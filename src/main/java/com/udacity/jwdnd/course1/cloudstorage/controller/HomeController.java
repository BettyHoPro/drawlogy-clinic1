package com.udacity.jwdnd.course1.cloudstorage.controller;

import ch.qos.logback.core.hook.DelayingShutdownHook;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.ErrorMsgService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private StorageService storageService;
    private NoteService noteService;
    private UserService userService;
    private CredentialsService credentialsService;

    @Autowired
    ErrorMsgService errorMsgService;

    public  HomeController(StorageService storageService,NoteService noteService,UserService userService,CredentialsService credentialsService)
    {
        this.storageService=storageService;
        this.noteService=noteService;
        this.userService=userService;
        this.credentialsService=credentialsService;
    }

    @GetMapping
    public String getHomePage() {
        return "home";
    }
    @PostMapping("/note")
    public String insOrUppdNote(Authentication authentication, Notes noteFromHome, RedirectAttributes redirectAttributes)
    {
        try {
            if(Integer.parseInt(noteFromHome.getNoteinsorupd()) >0) {
                redirectAttributes.addFlashAttribute("noteMsgShowOrNotShow",true);
                redirectAttributes.addFlashAttribute("classnotemessage",errorMsgService.getClassSuccessType());
                redirectAttributes.addFlashAttribute("noteNavErrorMeesage",errorMsgService.getNoteSuccessUpdate());
                noteService.updateNote(noteFromHome, userService.getUser(authentication.getName()).getUserId(), Integer.parseInt(noteFromHome.getNoteinsorupd()));
            }
            else {
                redirectAttributes.addFlashAttribute("noteMsgShowOrNotShow",true);
                redirectAttributes.addFlashAttribute("classnotemessage",errorMsgService.getClassSuccessType());
                redirectAttributes.addFlashAttribute("noteNavErrorMeesage",errorMsgService.getNoteSuccessInsert());
                noteService.insNewNote(noteFromHome, userService.getUser(authentication.getName()).getUserId());
            }

            return "redirect:/home";
        }
        catch (Exception e)
        {
            System.out.println(e.getCause());
            redirectAttributes.addFlashAttribute("noteMsgShowOrNotShow",true);
            redirectAttributes.addFlashAttribute("classnotemessage",errorMsgService.getClassErrorType());
            redirectAttributes.addFlashAttribute("noteNavErrorMeesage",errorMsgService.getNoteSomethingWrong());
            return "redirect:/home";
        }

    }
    @PostMapping("/credential")
    public String insOrUpdcredential(Authentication authentication,  Credentials credentialsPost,RedirectAttributes redirectAttributes)
    {
        try {


            if (Integer.parseInt(credentialsPost.getCredentialInsOrUpd()) > 0) {
                redirectAttributes.addFlashAttribute("credentialMsgShowOrNotShow", true);
                redirectAttributes.addFlashAttribute("classCredentialmessage", errorMsgService.getClassSuccessType());
                redirectAttributes.addFlashAttribute("credentialNavErrorMeesage", errorMsgService.getCredentialSuccessUpdate());
                credentialsService.updateCredential(credentialsPost, Integer.parseInt(credentialsPost.getCredentialInsOrUpd()), userService.getUser(authentication.getName()).getUserId());
            } else {
                redirectAttributes.addFlashAttribute("credentialMsgShowOrNotShow", true);
                redirectAttributes.addFlashAttribute("classCredentialmessage", errorMsgService.getClassSuccessType());
                redirectAttributes.addFlashAttribute("credentialNavErrorMeesage", errorMsgService.getCredentialSuccessInsert());
                credentialsService.insCredentials(authentication, credentialsPost);
            }
            return "redirect:/home";
        }
        catch (Exception e)
        {

            redirectAttributes.addFlashAttribute("credentialMsgShowOrNotShow", true);
            redirectAttributes.addFlashAttribute("classCredentialmessage", errorMsgService.getClassErrorType());
            redirectAttributes.addFlashAttribute("credentialNavErrorMeesage", errorMsgService.getCredentialSomethingWrong());
            return "redirect:/home";
        }
    }

    @ModelAttribute("credentialsList")
    public List<Credentials> allCredentials(Authentication authentication)
    {
        return credentialsService.getAllCredentials(authentication.getName());
    }

    @ModelAttribute("credentialsPost")
    public Credentials setFormCredentials(){return new Credentials();}
    @ModelAttribute("noteslist")
    public List<Notes> allNotes(Authentication authentication)
    {
        return noteService.loadAllNotes(authentication);
    }
    @ModelAttribute("files")
    public List<File> allFiles(Authentication authentication)
    {
        return storageService.loadAllFile(authentication);
    }

    @ModelAttribute("noteFromHome")
    public Notes setformnodes()
    {
        return new Notes();
    }





    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable int id,RedirectAttributes redirectAttributes) {

        Notes notes=noteService.loadNoteById(id);
        redirectAttributes.addFlashAttribute("noteMsgShowOrNotShow",true);
        redirectAttributes.addFlashAttribute("classnotemessage",errorMsgService.getClassSuccessType());
        redirectAttributes.addFlashAttribute("noteNavErrorMeesage",errorMsgService.getNoteSucessfullDelete(notes.getNotetitle()));
        noteService.delNote(id);
        return "redirect:/home";
    }
    @GetMapping("/credential/delete/{id}")
    public String deletecredential(@PathVariable int id,RedirectAttributes redirectAttributes) {
        Credentials credentials=credentialsService.getCredentialById(id);
        redirectAttributes.addFlashAttribute("credentialMsgShowOrNotShow", true);
        redirectAttributes.addFlashAttribute("classCredentialmessage", errorMsgService.getClassSuccessType());
        redirectAttributes.addFlashAttribute("credentialNavErrorMeesage", errorMsgService.getCredentialSucessfullDelete(credentials.getUrl()));
        credentialsService.delCredentials(id);
        return "redirect:/home";
    }



}
