package com.ciazhar.mongoauthserver.model;

import com.ciazhar.mongoauthserver.util.SerializableObjectConverter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "authorization_token")
public class MongoAuthorizationCode {

    public static final String CODE = "code";

    @Id
    private String id;
    private String code;
    private String authentication;
    
    public OAuth2Authentication getAuthentication() {
        return SerializableObjectConverter.deserialize(authentication);
    }

    public void setAuthentication(OAuth2Authentication authentication) {
        this.authentication = SerializableObjectConverter.serialize(authentication);;
    }
}