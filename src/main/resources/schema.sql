
CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR(100),
  password VARCHAR(100),
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    fk_userid INT,
    foreign key (fk_userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    filename VARCHAR(150),
    contenttype VARCHAR(150),
    filesize VARCHAR(100),
    fk_userid INT,
    filedata mediumblob,
    instime datetime,
    foreign key (fk_userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR(30),
    key VARCHAR(150),
    password VARCHAR(150),
    fk_userid INT,
    foreign key (fk_userid) references USERS(userid)
);