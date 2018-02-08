package com.ciazhar.mongoauthserver;

import javax.annotation.PostConstruct;

import com.ciazhar.mongoauthserver.model.MongoAccessToken;
import com.ciazhar.mongoauthserver.model.MongoAuthorizationCode;
import com.ciazhar.mongoauthserver.model.MongoClientDetails;
import com.ciazhar.mongoauthserver.model.MongoUser;
import com.google.common.collect.Sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.AuthorityUtils;

@SpringBootApplication
public class MongoAuthServerApplication {

	@Autowired MongoTemplate mongoTemplate;

    @PostConstruct
    void init(){
        
        mongoTemplate.dropCollection(MongoUser.class);
        mongoTemplate.dropCollection(MongoClientDetails.class);
        mongoTemplate.dropCollection(MongoAccessToken.class);
        mongoTemplate.dropCollection(MongoAuthorizationCode.class);

        // init the users
		MongoUser mongoUser = MongoUser.builder()
			.username("user")
			.password("user")
			.roles(Sets.newHashSet(("ROLE_USER")))
			.build();
        mongoTemplate.save(mongoUser);

        // init the client details
        MongoClientDetails clientDetails = MongoClientDetails.builder().build();
        clientDetails.setClientId("web-client");
        clientDetails.setClientSecret("web-client-secret");
        clientDetails.setSecretRequired(true);
        clientDetails.setResourceIds(Sets.newHashSet("foo"));
        clientDetails.setScope(Sets.newHashSet("read-foo"));
        clientDetails.setAuthorizedGrantTypes(Sets.newHashSet("authorization_code", "refresh_token",
                "password", "client_credentials"));
        clientDetails.setRegisteredRedirectUri(Sets.newHashSet("http://localhost:8082/resource-service"));
        clientDetails.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
        clientDetails.setAccessTokenValiditySeconds(60);
        clientDetails.setRefreshTokenValiditySeconds(14400);
        clientDetails.setAutoApprove(false);
        mongoTemplate.save(clientDetails);

    }

	public static void main(String[] args) {
		SpringApplication.run(MongoAuthServerApplication.class, args);
	}
}
