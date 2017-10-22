package org.x2b.studi.core;

import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;
import graphql.schema.idl.RuntimeWiring;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

import java.util.List;

public class TestGraphQLServiceConfigure {
    private class TestConfigure extends GraphQLServiceConfigure {
        public class Hello {

            public Hello() {

            }

            public String getValue() {
                return "this class is for testing schema creation but does not contain tests";
            }
        }
        

        @Override
        protected RuntimeWiring createRuntimeWiring() {
            return RuntimeWiring.newRuntimeWiring()
                    .type("QueryRoot", w -> w
                            .dataFetcher("getHello", environment -> {
                                return new Hello();
                            })
                    )
                    .type("MutationRoot", w -> w
                            .dataFetcher("logAString", env -> "mutation")
                    )
                    .build();
        }
    }


    @Test
    public void testCreateSchema() {
        TestConfigure configure = new TestConfigure();
        GraphQLSchema schema = configure.schema();
        Assert.assertNotNull(schema);

        GraphQLType queryRoot = schema.getQueryType();
        Assert.assertNotNull(queryRoot);
        Assert.assertEquals("QueryRoot", queryRoot.getName());

        GraphQLType mutRoot = schema.getMutationType();
        Assert.assertNotNull(mutRoot);
        Assert.assertEquals("MutationRoot", mutRoot.getName());

        List<GraphQLType> types = schema.getAllTypesAsList();
        Assert.assertEquals(types.toString(), 3 + 10, types.size()); //10 built in
    }
}
