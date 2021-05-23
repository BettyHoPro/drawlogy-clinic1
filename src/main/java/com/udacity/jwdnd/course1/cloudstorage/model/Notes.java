package com.udacity.jwdnd.course1.cloudstorage.model;

public class Notes {
    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer fk_userid;
    private String noteinsorupd;

    public String getNoteinsorupd() {
        return noteinsorupd;
    }

    public void setNoteinsorupd(String noteinsorupd) {
        this.noteinsorupd = noteinsorupd;
    }

    public Notes(Integer noteid, String notetitle, String notedescription, Integer fk_userid) {
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
        this.fk_userid = fk_userid;
    }

    public Notes( String notetitle, String notedescription) {
        this.notetitle = notetitle;
        this.notedescription = notedescription;
    }

    public Notes() {
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }


    public Integer getFk_userid() {
        return fk_userid;
    }

    public void setFk_userid(Integer fk_userid) {
        this.fk_userid = fk_userid;
    }
}
