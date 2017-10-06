package org.x2b.study.core;

import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.x2b.study.core.graphql.fetchers.CreateEmptyUserFetcher;

@SpringBootApplication
public class IntegrationTestService extends GraphQLServiceConfigure {


    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestService.class, args);
    }



    protected RuntimeWiring createRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("MutationRoot", w -> w
                    .dataFetcher("createEmptyUser", new CreateEmptyUserFetcher())
                )
                .type("QueryRoot", w -> w
                    .dataFetcher("getAStr", new StaticDataFetcher("foo"))
                )
                .build();
    }
}
