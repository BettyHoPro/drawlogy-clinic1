package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.storage.StorageProperties;
import com.udacity.jwdnd.course1.cloudstorage.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CloudStorageApplication {

	public static void main(String[] args) {
/*		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		String test="testmikonim";
		String result="";
		EncryptionService encryptionService=new EncryptionService();
		result=encryptionService.encryptValue(test,encodedSalt);
		test=encryptionService.decryptValue(result,encodedSalt);*/


		SpringApplication.run(CloudStorageApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

/*	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver
				= new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(5242880);
		return multipartResolver;
	}*/

}
