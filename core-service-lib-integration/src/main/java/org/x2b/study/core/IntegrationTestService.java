package org.x2b.study.core;

import graphql.schema.idl.RuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.x2b.study.core.graphql.fetchers.mutation.createuser.CreateUserFetcher;
import org.x2b.study.core.graphql.fetchers.mutation.getsecure.SecureTestFetcher;
import org.x2b.study.core.graphql.fetchers.query.getuser.GetUserFetcher;
import org.x2b.study.core.security.data.mongodb.AuthorizationRepository;

@SpringBootApplication
@ComponentScan(basePackages = "org.x2b.study.core.*")
public class IntegrationTestService extends GraphQLServiceConfigure {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(IntegrationTestService.class, args);
        System.out.println(ctx.getBean("authenticatingRealm"));
    }

    @Autowired
    public AuthorizationRepository authRepo;

    protected RuntimeWiring createRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("MutationRoot", w -> w
                    .dataFetcher("createUser", new CreateUserFetcher(authRepo))
                )
                .type("QueryRoot", w -> w
                    .dataFetcher("getUserPermissions", new GetUserFetcher(authRepo))
                    .dataFetcher("secureGet", new SecureTestFetcher())
                )
                .build();
    }


}
