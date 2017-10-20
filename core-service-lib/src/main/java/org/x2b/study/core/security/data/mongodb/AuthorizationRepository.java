package org.x2b.study.core.security.data.mongodb;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorizationRepository extends MongoRepository<AuthenticatedUser, UUID> {

    String AUTHORIZED_USER_CACHE = "authorizedUser";

    @Override
    @Cacheable(AUTHORIZED_USER_CACHE)
    AuthenticatedUser findOne(UUID uuid);


    @Override
    @CacheEvict(AUTHORIZED_USER_CACHE)
    AuthenticatedUser save(AuthenticatedUser entity);
}
