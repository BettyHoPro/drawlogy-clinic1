package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
    private Integer credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private String decryptedPassword;
    private Integer fk_userid;
    private String credentialInsOrUpd;

    public String getCredentialInsOrUpd() {
        return credentialInsOrUpd;
    }

    public void setCredentialInsOrUpd(String credentialInsOrUpd) {
        this.credentialInsOrUpd = credentialInsOrUpd;
    }

    public Credentials(String url, String username, String key, String password, Integer fk_userid) {

        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.fk_userid = fk_userid;
    }

    public Credentials(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public Credentials(String url, String username, String password,String key) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.key=key;
    }

    public Credentials() {
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }



    public Integer getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(Integer credentialid) {
        this.credentialid = credentialid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFk_userid() {
        return fk_userid;
    }

    public void setFk_userid(Integer fk_userid) {
        this.fk_userid = fk_userid;
    }
}
