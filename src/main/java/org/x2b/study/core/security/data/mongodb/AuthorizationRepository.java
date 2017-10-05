package org.x2b.study.core.security.data.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AuthorizationRepository extends MongoRepository<AuthenticatedUser, UUID> {
}
