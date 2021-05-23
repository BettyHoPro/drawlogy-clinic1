package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    @FindBy (id="logoutbtn")
    private WebElement logoutbtn;

    @FindBy (id="nav-files-tab")
    private WebElement navfilestab;
    @FindBy (id="nav-notes-tab")
    private WebElement navnotestab;
    @FindBy (id="nav-credentials-tab")
    private WebElement navcredentialstab;

    //File tab
    @FindBy (id="fileUpload")
    private WebElement fileUpload;
    @FindBy (id="uploadBtn")
    private WebElement uploadBtn;

    //Note tab
    @FindBy (id="add-new-note")
    private WebElement addnewnote;
    @FindBy (id="note-title")
    private WebElement notetitle;
    @FindBy (id="note-description")
    private WebElement notedescription;
    @FindBy (id="noteSubmit")
    private WebElement noteSubmit;
    @FindBy (id="note-title-record")
    private WebElement noteTitleRecord;
    @FindBy (id="note-description-record")
    private WebElement noteDescriptionRecord;
    @FindBy (id="noteSubmit-javascript")
    private WebElement noteSubmitJavascript;
    @FindBy (id="edit-note")
    private WebElement editnote;
    @FindBy (id="delete-note")
    private WebElement deleteNote;

    //Credential tab
    @FindBy (id="add-new-credential")
    private WebElement addnewcredential;
    @FindBy (id="credential-url")
    private WebElement credentialurl;
    @FindBy (id="credential-username")
    private WebElement credentialusername;
    @FindBy (id="credential-password")
    private WebElement credentialpassword;
    @FindBy (id="credentialSubmit-javascript")
    private WebElement credentialSubmitJavascript;
    @FindBy (id="credentialEditId1")
    private WebElement credentialEditId1;
    @FindBy (id="credentialEditId2")
    private WebElement credentialEditId2;
    @FindBy (id="credentialEditId3")
    private WebElement credentialEditId3;
    @FindBy (id="credential-Close-btn-EditMode")
    private WebElement credentialCloseBtnEditMode;
    @FindBy (id="delete-credential")
    private WebElement deleteCredential;

    public WebElement getlogoutbtn() {
        return logoutbtn;
    }

    public void setlogoutbtn(WebElement logout) {
        this.logoutbtn = logout;
    }

    public WebElement getNoteSubmit() {
        return noteSubmit;
    }

    public void setNoteSubmit(WebElement noteSubmit) {
        this.noteSubmit = noteSubmit;
    }

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getLogoutbtn() {
        return logoutbtn;
    }

    public void setLogoutbtn(WebElement logoutbtn) {
        this.logoutbtn = logoutbtn;
    }

    public WebElement getNavfilestab() {
        return navfilestab;
    }

    public void setNavfilestab(WebElement navfilestab) {
        this.navfilestab = navfilestab;
    }

    public WebElement getNavnotestab() {
        return navnotestab;
    }

    public void setNavnotestab(WebElement navnotestab) {
        this.navnotestab = navnotestab;
    }

    public WebElement getNavcredentialstab() {
        return navcredentialstab;
    }

    public void setNavcredentialstab(WebElement navcredentialstab) {
        this.navcredentialstab = navcredentialstab;
    }

    public void logOut()
    {
        logoutbtn.click();
    }

    public void add_new_note(Notes notes)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navnotestab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addnewnote);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + notes.getNotetitle() + "';", notetitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + notes.getNotedescription() + "';", notedescription);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteSubmitJavascript);
    }

    public void changeNavbarToNote()
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navnotestab);
    }
    public void changeNavbarToCredential()
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navcredentialstab);
    }
    public Notes getFirstNote()
    {
        Notes notes=new Notes();
        String stTitle=(((JavascriptExecutor) driver).executeScript("return document.getElementById('note-title-record');")!=null ? (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('note-title-record').innerHTML;"): "");
        String stDescription=(((JavascriptExecutor) driver).executeScript("return document.getElementById('note-description-record');")!=null ? (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('note-description-record').innerHTML;") : "");
        notes.setNotetitle(stTitle);
        notes.setNotedescription(stDescription);
        return notes;
    }

    public void changeFirstNote(Notes changedNote)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",editnote );
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + changedNote.getNotetitle() + "';", notetitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + changedNote.getNotedescription() + "';", notedescription);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteSubmitJavascript);

    }

    public void deleteFirstNote()
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",deleteNote );
    }

    public void insSomeCredentials(List<Credentials> credentialsList)
    {
        for (int i=0;i<credentialsList.size();i++)
        {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navcredentialstab);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addnewcredential);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + credentialsList.get(i).getUrl() + "';", credentialurl);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + credentialsList.get(i).getUsername() + "';", credentialusername);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + credentialsList.get(i).getPassword() + "';", credentialpassword);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialSubmitJavascript);
        }
    }

    public List<Credentials> getAddedCredentialList(){

        List<Credentials> credentialsList=new ArrayList<Credentials>();

        credentialsList.add(new Credentials(
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId1');")!=null ? (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId1').getAttribute('data-url');"):"" ),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId1');")!=null ? (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId1').getAttribute('data-username');"):"" ),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId1');")!=null?(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId1').getAttribute('data-password');") :""),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId1');")!=null?(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId1').getAttribute('data-key');"):"")

        ));

        credentialsList.add(new Credentials(
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId2');")!=null ? (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId2').getAttribute('data-url');"):"" ),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId2');")!=null ? (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId2').getAttribute('data-username');"):"" ),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId2');")!=null?(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId2').getAttribute('data-password');") :""),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId2');")!=null?(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId2').getAttribute('data-key');"):"")
        ));

        credentialsList.add(new Credentials(
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId3');")!=null ? (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId3').getAttribute('data-url');"):"" ),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId3');")!=null ? (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId3').getAttribute('data-username');"):"" ),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId3');")!=null?(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId3').getAttribute('data-password');") :""),
                ( ((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId3');")!=null?(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credentialEditId3').getAttribute('data-key');"):"")
        ));


        return  credentialsList;

    }



    public List<Credentials> getAddedCredentialListEditView() {
        List<Credentials> credentialsList=new ArrayList<Credentials>();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialEditId1);

        credentialsList.add(new Credentials(
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-url').getAttribute('urlResult');"),
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-username').getAttribute('usernameResult');"),
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-password').getAttribute('decryptPasswprdResult');")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialCloseBtnEditMode);


        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialEditId2);

        credentialsList.add(new Credentials(
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-url').getAttribute('urlResult');"),
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-username').getAttribute('usernameResult');"),
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-password').getAttribute('decryptPasswprdResult');")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialCloseBtnEditMode);


        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialEditId3);

        credentialsList.add(new Credentials(
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-url').getAttribute('urlResult');"),
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-username').getAttribute('usernameResult');"),
                (String)((JavascriptExecutor) driver).executeScript("return document.getElementById('credential-password').getAttribute('decryptPasswprdResult');")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialCloseBtnEditMode);

        return  credentialsList;
    }


    public void changeCredentialsValue(List<Credentials> inCredetials)
    {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navcredentialstab);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialEditId1);

            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(0).getUrl() + "';", credentialurl);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(0).getUsername() + "';", credentialusername);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(0).getPassword() + "';", credentialpassword);
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialSubmitJavascript);


            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navcredentialstab);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialEditId2);

            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(1).getUrl() + "';", credentialurl);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(1).getUsername() + "';", credentialusername);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(1).getPassword() + "';", credentialpassword);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialSubmitJavascript);


            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navcredentialstab);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialEditId3);

            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(2).getUrl() + "';", credentialurl);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(2).getUsername() + "';", credentialusername);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + inCredetials.get(2).getPassword() + "';", credentialpassword);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", credentialSubmitJavascript);
    }

    public void deleteCredentials()
    {
         List<WebElement> allDeleteElements= driver.findElements(By.id("delete-credential"));
         for (int i=0; i<allDeleteElements.size();i++) {
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteCredential);
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", navcredentialstab);
         }
    }
}
