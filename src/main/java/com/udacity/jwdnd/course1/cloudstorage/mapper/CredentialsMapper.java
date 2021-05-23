package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("select * from credentials WHERE username = #{username}")
    List<Credentials> getCredentials(String username);

    @Select("select * from credentials WHERE fk_userid = #{fk_userid}")
    List<Credentials> getAllCredentials(int fk_userid);


    @Select("select * from credentials WHERE credentialid = #{credentialid}")
    Credentials getOneCredential(int credentialid);

    @Insert("INSERT INTO credentials (url, username, `key`, `password`, fk_userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{fk_userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid",keyColumn = "credentialid")
    //int insertCredentials(String url,String username,String key1,String password,int fk_userid);
    int insertCredentials(Credentials credentials);

    @Delete("delete from credentials where credentialid=#{credentialid}")
    void deleteById(int credentialid);

    @Update("update  credentials set  url=#{url}, username=#{username},`password`=#{password} where credentialid=#{credentialid}")
    int updateCredentials(Credentials credentials);
}
