package org.x2b.study.core;


import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.File;

public abstract class GraphQLServiceConfigure {

    @Value("#{graphql.schema.schemaFileLocation}")
    public static String schemaFileLocation = "schema.gql";


    @Bean
    public GraphQLSchema schema() {
        SchemaParser parser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        TypeDefinitionRegistry tdr = parser.parse(getSchemaFile());
        RuntimeWiring runtimeWiring = createRuntimeWiring();
        if (runtimeWiring == null) {
            return null;
        }
        return schemaGenerator.makeExecutableSchema(tdr, runtimeWiring);
    }

    private File getSchemaFile() {
        return new File(this.getClass().getClassLoader().getResource(schemaFileLocation).getFile());
    }


    protected RuntimeWiring createRuntimeWiring() {
        return null;
    }
}
