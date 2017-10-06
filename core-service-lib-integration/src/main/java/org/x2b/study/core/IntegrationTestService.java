package org.x2b.study.core;

import graphql.schema.idl.RuntimeWiring;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.x2b.study.core.graphql.fetchers.CreateEmptyUserFetcher;

@SpringBootApplication
public class IntegrationTestService extends GraphQLServiceConfigure implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestService.class, args);
    }

    @Override
    protected RuntimeWiring createRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("MutationRoot", w -> w
                    .dataFetcher("createEmptyUser", new CreateEmptyUserFetcher())
                ).build();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
