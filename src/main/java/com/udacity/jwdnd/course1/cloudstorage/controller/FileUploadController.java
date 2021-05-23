package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.ErrorMsgService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.storage.StorageFileNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@RequestMapping("/home/fileupload")
@Controller
public class FileUploadController {

    private final StorageService storageService;
    private UserService userService;
    private ErrorMsgService errorMsgService;

    @Autowired
    public FileUploadController(StorageService storageService,UserService userService,ErrorMsgService errorMsgService) {
        this.storageService = storageService;
        this.userService=userService;
        this.errorMsgService=errorMsgService;
    }

    @GetMapping()
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "home";
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable int id) {

        File file = storageService.loadSingleFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file.getFiledata());
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable int id,Model model,RedirectAttributes redirectAttributes) {

        File file=storageService.loadSingleFile(id);
        storageService.deleteById(id);
        redirectAttributes.addFlashAttribute("repeatcheck", true);
        redirectAttributes.addFlashAttribute("classmessage",errorMsgService.getClassSuccessType());
        redirectAttributes.addFlashAttribute("fileNavErrorMeesage",errorMsgService.getSucessfullFileDelete(file.getFilename()));
        return "redirect:/home";
    }

    @PostMapping()
    public String handleFileUpload(Authentication authentication, @RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException
    {
        if (!storageService.checkFileExistanse(file.getOriginalFilename())) {
            redirectAttributes.addFlashAttribute("repeatcheck", true);
            redirectAttributes.addFlashAttribute("classmessage",errorMsgService.getClassErrorType());
            redirectAttributes.addFlashAttribute("fileNavErrorMeesage",errorMsgService.getDoplicateFile());
        }
        else if(file.getOriginalFilename().equals(""))
        {
            redirectAttributes.addFlashAttribute("repeatcheck", true);
            redirectAttributes.addFlashAttribute("classmessage",errorMsgService.getClassErrorType());
            redirectAttributes.addFlashAttribute("fileNavErrorMeesage",errorMsgService.getEmptyFile());
        }
        else if (file.getSize()<=0)
        {

            redirectAttributes.addFlashAttribute("repeatcheck", true);
            redirectAttributes.addFlashAttribute("classmessage",errorMsgService.getClassErrorType());
            redirectAttributes.addFlashAttribute("fileNavErrorMeesage",errorMsgService.getZeroFileSize());
        }
        else if (file.getSize()>20500000)
        {

            redirectAttributes.addFlashAttribute("repeatcheck", true);
            redirectAttributes.addFlashAttribute("classmessage",errorMsgService.getClassErrorType());
            redirectAttributes.addFlashAttribute("fileNavErrorMeesage",errorMsgService.getFileSizeLimitError());
        }

        else {

           if( storageService.store(file, userService.getUser(authentication.getName()).getUserId())==1)
           {
               redirectAttributes.addFlashAttribute("repeatcheck", true);
               redirectAttributes.addFlashAttribute("classmessage",errorMsgService.getClassSuccessType());
               redirectAttributes.addFlashAttribute("fileNavErrorMeesage",errorMsgService.getSuccessfullFileUpload(file.getOriginalFilename()));
           }
           else {
               redirectAttributes.addFlashAttribute("repeatcheck", true);
               redirectAttributes.addFlashAttribute("classmessage",errorMsgService.getClassErrorType());
               redirectAttributes.addFlashAttribute("fileNavErrorMeesage",errorMsgService.getFileDidNotInsert());
           }
        }
        return "redirect:/home";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}