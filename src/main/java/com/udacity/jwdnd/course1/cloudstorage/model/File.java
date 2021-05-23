package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

public class File {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer fk_userid;
    private byte[] filedata;
    private LocalDateTime instime;

    public LocalDateTime getInstime() {
        return instime;
    }

    public void setInstime(LocalDateTime instime) {
        this.instime = instime;
    }

    public File(MultipartFile mfile) throws IOException {
        this.filename = mfile.getOriginalFilename();
        this.contenttype = mfile.getContentType();
        this.filesize =String.valueOf(mfile.getSize());
        this.filedata = mfile.getBytes();
        this.instime= LocalDateTime.now();
    }

    public File() {
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getFk_userid() {
        return fk_userid;
    }

    public void setFk_userid(Integer fk_userid) {
        this.fk_userid = fk_userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }


}
