package org.x2b.study.core.graphql.fetchers.mutation.createuser;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.x2b.study.core.security.data.mongodb.AuthenticatedUser;
import org.x2b.study.core.security.data.mongodb.AuthorizationRepository;

import java.util.*;

public class CreateUserFetcher implements DataFetcher<String> {


    private AuthorizationRepository authRepo;

    public CreateUserFetcher(AuthorizationRepository authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public String get(DataFetchingEnvironment environment) {
        HashMap<String, Object> input = environment.getArgument("input");
        List<String> permissions = (List<String>) input.get("permissions");
        AuthenticatedUser user = new AuthenticatedUser(UUID.randomUUID(), new HashSet<>(permissions));
        authRepo.save(user);
        return user.getUUID().toString();
    }
}
