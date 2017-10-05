package org.x2b.study.core;


import com.coxautodev.graphql.tools.*;
import graphql.schema.GraphQLSchema;

import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.x2b.study.core.graphql.fetchers.HelloFetcher;
import org.x2b.study.core.graphql.resolvers.MutationResolver;
import org.x2b.study.core.graphql.resolvers.QueryResolver;
import org.x2b.study.core.graphql.resolvers.hello.GetComplexThingResolver;

import java.io.File;

@SpringBootApplication
public abstract class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

//    @Bean
//    GraphQLSchema schema() {
//        return SchemaParser.newParser()
//                .file("schema.gql")
//                .resolvers(getAllResolvers())
//                .build()
//                .makeExecutableSchema();
//    }

    @Bean
    public GraphQLSchema schema() {
        graphql.schema.idl.SchemaParser parser = new graphql.schema.idl.SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        TypeDefinitionRegistry tdr = parser.parse(getSchemaFile());
        return schemaGenerator.makeExecutableSchema(tdr, makeRuntimeWiring());
    }

    private File getSchemaFile() {
        return new File(this.getClass().getClassLoader().getResource("schema.gql").getFile());
    }

    private RuntimeWiring makeRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("hello", new HelloFetcher())
                ).build();
    }

    public GraphQLResolver<?>[] getAllResolvers() {
        return new GraphQLResolver[] {new QueryResolver(), new MutationResolver(),
                new GetComplexThingResolver()};
    }

}
