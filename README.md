# Drawology Clinic
During the COVID-days, people are quite scared about the virus and are reluctant to go outside, let alone visit places like clinics that can be easily infected.  ALso, if mental health problems occur, people living abroad could also hardly consult with a good local psychologist because of their language barrier. Therefore our project will provide a platform where patients will be able to consult psychologists in a very special way, in which they will no longer be constrained by typing or speaking English or other prevailing language and also improve the fun and anticipation of seeing the psychologist--- "Drawology Clinic." 

To remedy this issue, patients will be provided the platform where they need to draw the picture and upload them to psychologists's website as their current mental states and situations without going outside and in turn, they will also get the feedback in form of the picture and some translated version sentences for them to understand, which will definitly help to improve the expectation of seeing a psychologist.

In this project, we will include the personal information management features in our "clinic" application, including:

1. **Simple File Storage:** Upload/download/remove files
2. **Note Management:** Add/update/remove text notes

Plus we are responsible for developing the server, website, and tests. we Use Java Spring Boot MVC and Maven for the backend 

## Login Page
this the part that both patients and psychologists can log in to the system and patient can upload their drwaings as well as psychologists can download the file and write down note for them.

## Login Page
Consist of Three Tab one for normal user to upload their drawings, second tab is for psychologists to put note for each drwaing and the third tab is for administration tasks like managing the accounts username and passwords. 

## Requirements and Roadmap

1. a drawing in jpg, png, or docx
2. some registered psychologists
3. Application tests with Selenium

### The Back-End
The back-end is all about security and connecting the front-end to database data and actions. 

1. Managing user access with Spring Security
 - You have to restrict unauthorized users from accessing pages other than the login and signup pages. To do this, you must create a security configuration class that extends the `WebSecurityConfigurerAdapter` class from Spring. Place this class in a package reserved for security and configuration. Often this package is called `security` or `config`.
 - Spring Boot has built-in support for handling calls to the `/login` and `/logout` endpoints. You have to use the security configuration to override the default login page with one of your own, discussed in the front-end section.
 - You also need to implement a custom `AuthenticationProvider` which authorizes user logins by matching their credentials against those stored in the database.  


2. Handling front-end calls with controllers
 - You need to write controllers for the application that bind application data and functionality to the front-end. That means using Spring MVC's application model to identify the templates served for different requests and populating the view model with data needed by the template. 
 - The controllers you write should also be responsible for determining what, if any, error messages the application displays to the user. When a controller processes front-end requests, it should delegate the individual steps and logic of those requests to other services in the application, but it should interpret the results to ensure a smooth user experience.
 - It's a good idea to keep your controllers in a single package to isolate the controller layer. Usually, we simply call this package `controller`!
 - If you find yourself repeating tasks over and over again in controller methods, or your controller methods are getting long and complicated, consider abstracting some methods out into services! For example, consider the `HashService` and `EncryptionService` classes included in the starter code package `service`. These classes encapsulate simple, repetitive tasks and are available anywhere dependency injection is supported. Think about additional tasks that can be similarly abstracted and reused, and create new services to support them!


3. Making calls to the database with MyBatis mappers
 - Since you were provided with a database schema to work with, you can design Java classes to match the data in the database. These should be POJOs (Plain Old Java Objects) with fields that match the names and data types in the schema, and you should create one class per database table. These classes typically are placed in a `model` or `entity` package.
 - To connect these model classes with database data, implement MyBatis mapper interfaces for each of the model types. These mappers should have methods that represent specific SQL queries and statements required by the functionality of the application. They should support the basic CRUD (Create, Read, Update, Delete) operations for their respective models at the very least. You can place these classes in (you guessed it!) the `mapper` package.


### The Front-End
Your tech lead has done a thorough job developing HTML templates for the required application pages. They have included fields, modal forms, success and error message elements, as well as styling and functional components using Bootstrap as a framework. You must edit these templates and insert Thymeleaf attributes to supply the back-end data and functionality described by the following individual page requirements:

1. Login page
 - Everyone should be allowed access to this page, and users can use this page to login to the application. 
 - Show login errors, like invalid username/password, on this page. 


2. Sign Up page
 - Everyone should be allowed access to this page, and potential users can use this page to sign up for a new account. 
 - Validate that the username supplied does not already exist in the application, and show such signup errors on the page when they arise.
 - Remember to store the user's password securely!


3. Home page
The home page is the center of the application and hosts the three required pieces of functionality. The existing template presents them as three tabs that can be clicked through by the user:


 i. Files
  - The user should be able to upload files and see any files they previously uploaded. 

  - The user should be able to view/download or delete previously-uploaded files.
  - Any errors related to file actions should be displayed. For example, a user should not be able to upload two files with the same name, but they'll never know unless you tell them!


 ii. Notes
  - The user should be able to create notes and see a list of the notes they have previously created.
  - The user should be able to edit or delete previously-created notes.

 iii. Credentials
 - User should be able to change username and password with the help of this tab

The home page should have a logout button that allows the user to logout of the application and keep their data private.

### Testing
Your tech lead trusts you to do a good job, but testing is important whether you're an excel number-cruncher or a full-stack coding superstar! The QA team at Super*Duper*Drive carries out extensive user testing. Still, your tech lead wants you to write some simple Selenium tests to verify user-facing functionality and prove that your code is feature-complete before the testers get their hands on it.

1. Write tests for user signup, login, and unauthorized access restrictions.
 - Write a test that verifies that an unauthorized user can only access the login and signup pages.
 - Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible. 


2. Write tests for note creation, viewing, editing, and deletion.
 - Write a test that creates a note, and verifies it is displayed.
 - Write a test that edits an existing note and verifies that the changes are displayed.
 - Write a test that deletes a note and verifies that the note is no longer displayed.


3. Write tests for credential creation, viewing, editing, and deletion.
 - Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
 - Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
 - Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.

