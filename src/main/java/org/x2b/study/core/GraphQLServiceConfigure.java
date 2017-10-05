package org.x2b.study.core;


import graphql.schema.GraphQLSchema;

import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public abstract class GraphQLServiceConfigure {

    @Value("#{graphql.schema.schemaFileLocation}")
    public static String schemaFileLocation = "schema.gql";


    @Bean
    public GraphQLSchema schema() {
        graphql.schema.idl.SchemaParser parser = new graphql.schema.idl.SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        TypeDefinitionRegistry tdr = parser.parse(getSchemaFile());
        return schemaGenerator.makeExecutableSchema(tdr, createRuntimeWiring());
    }

    private File getSchemaFile() {
        return new File(this.getClass().getClassLoader().getResource(schemaFileLocation).getFile());
    }


    protected abstract RuntimeWiring createRuntimeWiring();
}
