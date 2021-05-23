package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private String baseURL;
	private UserMapper userMapper;
	private WebDriverWait wait;
	private WebDriver driver;
	private EncryptionService encryptionService;



	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + port;
	}

	@Test
	void UnauthorizedAccesstest()
	{
		driver.get(baseURL+"/home");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/home/notexist");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/notexist");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/home/note/delete/2");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/home/credential/delete/2");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/home/credential/delete/2");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

	}

	@Test
	void signupNewUserAndLogoutTest()
	{
		User user=new User(null,"testuser","","testpasword","testfirstname","testLastname");
	//	userMapper.deleteuser(user.getUsername());

		driver.get(baseURL+"/signup");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.setUpNewUser(user);


		driver.get(baseURL+"/login");
		LoginPage loginPage=new LoginPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getSubmitbutton()));
		loginPage.login(user);



		HomePage homePage =new HomePage(driver);
		wait = new WebDriverWait(driver, 10);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", homePage.getlogoutbtn());



		assertEquals("Login",driver.getTitle());

		driver.get(baseURL+"/home");
		assertEquals("Login",driver.getTitle());


	}


	@Test
	void createNote()
	{
		Notes note=new Notes("test title","test description");
		User user=new User(null,"testuser","","testpasword","testfirstname","testLastname");

		driver.get(baseURL+"/signup");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.setUpNewUser(user);


		driver.get(baseURL+"/login");
		LoginPage loginPage=new LoginPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getSubmitbutton()));
		loginPage.login(user);

		HomePage homePage =new HomePage(driver);

		homePage.changeNavbarToNote();
		homePage.deleteFirstNote();
		homePage.changeNavbarToNote();

		homePage.add_new_note(note);


		homePage.changeNavbarToNote();

		Notes result=homePage.getFirstNote();
		assertEquals(note.getNotetitle(),result.getNotetitle() );
		assertEquals(note.getNotedescription(),result.getNotedescription() );


	}

	@Test
	void editExistingNote()
	{
		Notes note=new Notes("test title","test description");
		Notes changednote=new Notes("changedtitle","changeddescription");
		User user=new User(null,"testuser","","testpasword","testfirstname","testLastname");

		driver.get(baseURL+"/signup");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.setUpNewUser(user);


		driver.get(baseURL+"/login");
		LoginPage loginPage=new LoginPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getSubmitbutton()));
		loginPage.login(user);

		HomePage homePage =new HomePage(driver);
		homePage.add_new_note(note);

		homePage.changeNavbarToNote();

		homePage.changeFirstNote(changednote);

		homePage.changeNavbarToNote();

		Notes result=homePage.getFirstNote();
		assertEquals(changednote.getNotetitle(),result.getNotetitle() );
		assertEquals(changednote.getNotedescription(),result.getNotedescription() );

	}

	@Test
	void deleteExistingNote() {
		Notes note=new Notes("test title","test description");
		User user=new User(null,"testuser","","testpasword","testfirstname","testLastname");

		driver.get(baseURL+"/signup");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.setUpNewUser(user);


		driver.get(baseURL+"/login");
		LoginPage loginPage=new LoginPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getSubmitbutton()));
		loginPage.login(user);

		HomePage homePage =new HomePage(driver);
		homePage.add_new_note(note);


		homePage.changeNavbarToNote();

		homePage.deleteFirstNote();

		homePage.changeNavbarToNote();

		Notes result=homePage.getFirstNote();
		assertEquals("",result.getNotetitle() );
		assertEquals("",result.getNotedescription() );
	}

	@Test
	void credentialTest()
	{
		this.encryptionService=new EncryptionService();
		User user=new User(null,"testuser","","testpasword","testfirstname","testLastname");
		List<Credentials> credentials=new ArrayList<Credentials>();
		credentials.add(new Credentials("http://www.testpage1.com","test1","testpasword1"));
		credentials.add(new Credentials("http://www.testpage2.com","test2","testpasword2"));
		credentials.add(new Credentials("http://www.testpage3.com","test3","testpasword3"));

		driver.get(baseURL+"/signup");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.setUpNewUser(user);


		driver.get(baseURL+"/login");
		LoginPage loginPage=new LoginPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getSubmitbutton()));
		loginPage.login(user);

		HomePage homePage =new HomePage(driver);

		homePage.changeNavbarToCredential();

		homePage.insSomeCredentials(credentials);

		homePage.changeNavbarToCredential();

		List<Credentials> credentialsList=homePage.getAddedCredentialList();

		assertEquals(encryptionService.decryptValue(credentialsList.get(0).getPassword(),credentialsList.get(0).getKey())   , credentials.get(0).getPassword());
		assertEquals(encryptionService.decryptValue(credentialsList.get(1).getPassword(),credentialsList.get(1).getKey())   , credentials.get(1).getPassword());
		assertEquals(encryptionService.decryptValue(credentialsList.get(2).getPassword(),credentialsList.get(2).getKey())   , credentials.get(2).getPassword());
	}

	@Test
	void editExistingCredentials()
	{
		this.encryptionService=new EncryptionService();
		User user=new User(null,"testuser","","testpasword","testfirstname","testLastname");
		List<Credentials> credentials=new ArrayList<Credentials>();
		credentials.add(new Credentials("http://www.testpage1.com","test1","testpasword1"));
		credentials.add(new Credentials("http://www.testpage2.com","test2","testpasword2"));
		credentials.add(new Credentials("http://www.testpage3.com","test3","testpasword3"));


		List<Credentials> credentialsChanged=new ArrayList<Credentials>();
		credentialsChanged.add(new Credentials("http://www.changedtestpage1.com","changedtest1","changedpasword1"));
		credentialsChanged.add(new Credentials("http://www.changedtestpage2.com","changedtest2","changedpasword2"));
		credentialsChanged.add(new Credentials("http://www.changedtestpage3.com","changedtest3","changedpasword3"));

		driver.get(baseURL+"/signup");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.setUpNewUser(user);


		driver.get(baseURL+"/login");
		LoginPage loginPage=new LoginPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getSubmitbutton()));
		loginPage.login(user);

		HomePage homePage =new HomePage(driver);

		homePage.changeNavbarToCredential();

		homePage.insSomeCredentials(credentials);

		homePage.changeNavbarToCredential();

		List<Credentials> credentialsListEditView=homePage.getAddedCredentialListEditView();

		assertEquals(credentials.get(0).getPassword(), credentialsListEditView.get(0).getPassword());
		assertEquals(credentials.get(1).getPassword(), credentialsListEditView.get(1).getPassword());
		assertEquals(credentials.get(2).getPassword(), credentialsListEditView.get(2).getPassword());


		homePage.changeCredentialsValue(credentialsChanged);

		homePage.changeNavbarToCredential();

		List<Credentials> credentialsListChanged=homePage.getAddedCredentialList();

		assertEquals(credentialsChanged.get(0).getUrl(), credentialsListChanged.get(0).getUrl());
		assertEquals(credentialsChanged.get(0).getUsername(), credentialsListChanged.get(0).getUsername());
		assertEquals(credentialsChanged.get(0).getPassword(), encryptionService.decryptValue(credentialsListChanged.get(0).getPassword(),credentialsListChanged.get(0).getKey()) );

		assertEquals(credentialsChanged.get(1).getUrl(), credentialsListChanged.get(1).getUrl());
		assertEquals(credentialsChanged.get(1).getUsername(), credentialsListChanged.get(1).getUsername());
		assertEquals(credentialsChanged.get(1).getPassword(), encryptionService.decryptValue(credentialsListChanged.get(1).getPassword(),credentialsListChanged.get(1).getKey()) );

		assertEquals(credentialsChanged.get(2).getUrl(), credentialsListChanged.get(2).getUrl());
		assertEquals(credentialsChanged.get(2).getUsername(), credentialsListChanged.get(2).getUsername());
		assertEquals(credentialsChanged.get(2).getPassword(), encryptionService.decryptValue(credentialsListChanged.get(2).getPassword(),credentialsListChanged.get(2).getKey()) );



	}

	@Test
	void deleteExistingCredentials() {

		User user=new User(null,"testuser","","testpasword","testfirstname","testLastname");
		List<Credentials> credentials=new ArrayList<Credentials>();
		credentials.add(new Credentials("http://www.testpage1.com","test1","testpasword1"));
		credentials.add(new Credentials("http://www.testpage2.com","test2","testpasword2"));
		credentials.add(new Credentials("http://www.testpage3.com","test3","testpasword3"));

		driver.get(baseURL+"/signup");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.setUpNewUser(user);


		driver.get(baseURL+"/login");
		LoginPage loginPage=new LoginPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getSubmitbutton()));
		loginPage.login(user);

		HomePage homePage =new HomePage(driver);

		homePage.changeNavbarToCredential();

		homePage.insSomeCredentials(credentials);

		homePage.changeNavbarToCredential();

		homePage.deleteCredentials();

		homePage.changeNavbarToCredential();


		List<Credentials> credList= homePage.getAddedCredentialList();

		assertEquals("", credList.get(0).getUrl());
		assertEquals("", credList.get(0).getUsername());
		assertEquals("", credList.get(0).getPassword());

	}
	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

}
