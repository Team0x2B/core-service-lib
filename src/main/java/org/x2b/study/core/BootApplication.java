package org.x2b.study.core;


import com.coxautodev.graphql.tools.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.GraphQLSchema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.x2b.study.core.resolvers.MutationResolver;
import org.x2b.study.core.resolvers.QueryResolver;

import java.io.File;

@SpringBootApplication
public abstract class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Bean
    GraphQLSchema schema() {

        SchemaParserOptions options = SchemaParserOptions
                .newOptions()
                .objectMapperConfigurer(getObjectMapperConfigurer())
                .build();
        return SchemaParser.newParser()
                .options(options)
                .file("schema.gql")
                .resolvers(new GraphQLResolver[]{new QueryResolver(), new MutationResolver()})
                .build()
                .makeExecutableSchema();
    }

    private ObjectMapperConfigurer getObjectMapperConfigurer() {
        return (mapper, context) -> {
                mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.NONE));
                mapper.setVisibility(mapper.getDeserializationConfig().getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.NONE));
        };
    }


}
