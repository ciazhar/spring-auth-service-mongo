package com.ciazhar.mongoauthserver.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "user")
public class MongoUser {

    @Id
    private String id;
    private String username;
    private String password;
    private Set<String> roles;
    
}