package org.x2b.study.core;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    GraphQLSchema schema() {
        return SchemaParser.newParser()
                .file("schema.gql")
                .resolvers(new GraphQLResolver[]{getRootQueryResolver(), getRootMutationResolver()})
                .build()
                .makeExecutableSchema();
    }

    protected GraphQLQueryResolver getRootQueryResolver() {
        return new QueryResolver();
    }

    protected GraphQLMutationResolver getRootMutationResolver() {
        return new MutationResolver();
    }
}
