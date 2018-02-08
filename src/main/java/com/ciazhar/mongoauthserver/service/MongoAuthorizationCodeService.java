package com.ciazhar.mongoauthserver.service;

import com.ciazhar.mongoauthserver.model.MongoAuthorizationCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

public class MongoAuthorizationCodeService extends RandomValueAuthorizationCodeServices{

    @Autowired private MongoTemplate mongoTemplate;

	@Override
	protected void store(String code, OAuth2Authentication authentication) {
        MongoAuthorizationCode authorizationCode = MongoAuthorizationCode.builder().code(code).build();
        authorizationCode.setAuthentication(authentication);

        mongoTemplate.save(authorizationCode);
	}

	@Override
	protected OAuth2Authentication remove(String code) {
		Query query = new Query();
        query.addCriteria(Criteria.where(MongoAuthorizationCode.CODE).is(code));
        OAuth2Authentication authentication = null;
        MongoAuthorizationCode authorizationCode = mongoTemplate.findOne(query, MongoAuthorizationCode.class);
        if (authorizationCode != null) {
            authentication = authorizationCode.getAuthentication();
        }
        return authentication;
	}

    
}