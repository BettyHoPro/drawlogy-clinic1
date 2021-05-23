package com.udacity.jwdnd.course1.cloudstorage.services;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.stereotype.Service;

@Service
public class ErrorMsgService {
    final private String doplicateFile="The repeated file cannot be added.";
    final private String successfullFileUpload="You successfully uploaded ";
    final private String classErrorType="alert alert-danger";
    final private String classSuccessType="alert alert-success";
    final private String emptyFile="Selected file name Should not be empty, or you did not select any file.";
    final private String zeroFileSize="Selected File Size Should not be empty.";
    final private String sucessfullFileDelete="You successfully Deleted ";
    final private String fileSizeLimitError="Exceed File size! file size must be less than 2 MB.";
    final private String fileDidNotInsert="Some Thing Went Wrong File Did Not Inserted";


    final private String noteSuccessUpdate="Note Updated successfully";
    final private String noteSuccessInsert="New Note Inserted.";
    final private String noteSomethingWrong="Some thing went wrong.";
    final private String noteSucessfullDelete="You successfully Deleted ";

    final private String credentialSuccessUpdate="Credential Updated successfully";
    final private String credentialSuccessInsert="New Credential Inserted.";
    final private String credentialSomethingWrong="Some thing went wrong.";
    final private String credentialSucessfullDelete="You successfully Deleted Credential for URL: ";

    public String getFileDidNotInsert() {
        return fileDidNotInsert;
    }

    public String getCredentialSuccessUpdate() {
        return credentialSuccessUpdate;
    }

    public String getCredentialSuccessInsert() {
        return credentialSuccessInsert;
    }

    public String getCredentialSomethingWrong() {
        return credentialSomethingWrong;
    }

    public String getCredentialSucessfullDelete(String url) {
        return credentialSucessfullDelete+url+" !!";
    }

    public String getNoteSomethingWrong() {
        return noteSomethingWrong;
    }

    public String getNoteSuccessUpdate() {
        return noteSuccessUpdate;
    }

    public String getNoteSuccessInsert() {
        return noteSuccessInsert;
    }

    public String getNoteSucessfullDelete(String notetitle) {
        return noteSucessfullDelete+notetitle+"!!";
    }

    public String getFileSizeLimitError() {
        return fileSizeLimitError;
    }

    public String getSucessfullFileDelete(String filename) {
        return sucessfullFileDelete +filename+" !!";
    }

    public String getZeroFileSize() {
        return zeroFileSize;
    }

    public String getEmptyFile() {
        return emptyFile;
    }

    public ErrorMsgService() {
    }

    public String getClassErrorType() {
        return classErrorType;
    }

    public String getClassSuccessType() {
        return classSuccessType;
    }

    public String getDoplicateFile() {
        return doplicateFile;
    }

    public String getSuccessfullFileUpload(String filename) {
        return successfullFileUpload +filename+" !";
    }
}
