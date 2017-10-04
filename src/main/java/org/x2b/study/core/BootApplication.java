package org.x2b.study.core;


import com.coxautodev.graphql.tools.*;
import graphql.schema.GraphQLSchema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.x2b.study.core.graphql.resolvers.MutationResolver;
import org.x2b.study.core.graphql.resolvers.QueryResolver;
import org.x2b.study.core.graphql.resolvers.hello.GetComplexThingResolver;

@SpringBootApplication
public abstract class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    GraphQLSchema schema() {
        return SchemaParser.newParser()
                .file("schema.gql")
                .resolvers(getAllResolvers())
                .build()
                .makeExecutableSchema();
    }

    public GraphQLResolver<?>[] getAllResolvers() {
        return new GraphQLResolver[] {new QueryResolver(), new MutationResolver(),
                new GetComplexThingResolver()};
    }

}
