package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;
    //private final HashService hashService;
    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;

    public UserService(UserMapper userMapper,CredentialsMapper credentialsMapper,EncryptionService encryptionService) {
        this.userMapper = userMapper;
        //this.hashService = hashService;
        this.encryptionService=encryptionService;
        this.credentialsMapper=credentialsMapper;
    }


    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = encryptionService.encryptValue(user.getPassword(), encodedSalt);
        User insUser=new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName());
        int retusr= userMapper.insert(insUser);
/*
        Optional<User> usr =Optional.of(insUser);
        int retcred=credentialsMapper.insertCredentials(new Credentials("",user.getUsername(),encodedSalt,hashedPassword,usr.get().getUserId()));
*/


        return retusr;
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
