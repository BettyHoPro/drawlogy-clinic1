package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {
    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;
    private UserService userService;

    public CredentialsService(CredentialsMapper credentialsMapper,EncryptionService encryptionService,UserService userService)
    {
        this.credentialsMapper=credentialsMapper;
        this.encryptionService=encryptionService;
        this.userService=userService;
    }

    public List<Credentials> getAllCredentials(String username)
    {
        List<Credentials> tempCredentials=credentialsMapper.getAllCredentials( userService.getUser(username).getUserId());
        tempCredentials.forEach(credentials -> credentials.setDecryptedPassword(encryptionService.decryptValue(credentials.getPassword(),credentials.getKey())) );
        return tempCredentials;
    }
    public void insCredentials(Authentication authentication,Credentials credentials)
    {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword=encryptionService.encryptValue(credentials.getPassword(), encodedKey);

       // User usr=userService.getUser(authentication.getName()).getUserId();
        int ret=credentialsMapper.insertCredentials(new Credentials(credentials.getUrl(),credentials.getUsername(),encodedKey,encryptedPassword,userService.getUser(authentication.getName()).getUserId()));
    }
    public void delCredentials(int credentialID)
    {
        credentialsMapper.deleteById(credentialID);
    }
    public void updateCredential(Credentials credentials, int credentialID, int userid) {
        Credentials cred=credentialsMapper.getOneCredential(credentialID);
        cred.setPassword(encryptionService.encryptValue(credentials.getPassword(), cred.getKey()));
        cred.setUrl(credentials.getUrl());
        cred.setUsername(credentials.getUsername());

        credentialsMapper.updateCredentials(cred);

    }

    public Credentials getCredentialById(int credentialID)
    {
        return credentialsMapper.getOneCredential(credentialID);
    }
}
