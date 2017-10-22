package org.x2b.studi.core.graphql.fetchers.query.getuser;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.x2b.studi.core.security.data.mongodb.AuthenticatedUser;
import org.x2b.studi.core.security.data.mongodb.AuthorizationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetUserFetcher implements DataFetcher<List<String>> {

    private AuthorizationRepository authRepo;

    public GetUserFetcher(AuthorizationRepository authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public List<String> get(DataFetchingEnvironment environment) {
        String idString = environment.getArgument("id");
        UUID uuid = UUID.fromString(idString);
        AuthenticatedUser user = authRepo.findOne(uuid);
        return new ArrayList<>(user.getPermissions());
    }
}
