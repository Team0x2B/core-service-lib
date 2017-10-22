package org.x2b.studi.core.security.data.mongodb;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AuthorizationRepository extends MongoRepository<AuthenticatedUser, UUID> {

    String AUTHORIZED_USER_CACHE = "authorizedUser";

    @Override
    @Cacheable(AUTHORIZED_USER_CACHE)
    AuthenticatedUser findOne(UUID uuid);


    @Override
    @CacheEvict(AUTHORIZED_USER_CACHE)
    AuthenticatedUser save(AuthenticatedUser entity);

    @Override
    @CacheEvict(AUTHORIZED_USER_CACHE)
    AuthenticatedUser insert(AuthenticatedUser user);

    @Override
    @CacheEvict(AUTHORIZED_USER_CACHE)
    void delete(AuthenticatedUser entity);
}
