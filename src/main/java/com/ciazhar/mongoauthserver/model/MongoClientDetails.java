package com.ciazhar.mongoauthserver.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "client_details")
public class MongoClientDetails implements ClientDetails{

    public static final String CLIENT_ID = "clientId";
    public static final String CLIENT_SECRET = "clientSecret";
    public static final String RESOURCE_IDS = "resourceIds";
    public static final String SCOPE = "scope";
    public static final String AUTHORIZED_GRANT_TYPES = "authorizedGrantTypes";
    public static final String REGISTERED_REDIRECT_URI = "registeredRedirectUri";
    public static final String AUTHORITIES = "authorities";
    public static final String ACCESS_TOKEN_VALIDITY_SECONDS = "accessTokenValiditySeconds";
    public static final String REFRESH_TOKEN_VALIDITY_SECONDS = "refreshTokenValiditySeconds";
    public static final String ADDITIONAL_INFORMATION = "additionalInformation";

    @Id
    private String id;
    private String clientId;
    private Set<String> resourceIds;
    private boolean secretRequired;
    private String clientSecret;
    private boolean scoped;
    private Set<String> scope;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUri;
    private Collection<GrantedAuthority> authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private boolean autoApprove;
    private Map<String, Object> additionalInformation;
    
    @Override
	public boolean isAutoApprove(String scope) {
		return autoApprove;
	}
}