package org.x2b.study.core.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.x2b.study.core.graphql.types.Hello;

public class QueryResolver implements GraphQLQueryResolver {
    public Hello hello(String foo) {
        return new Hello(foo); //usually will be someApi.makeSomeApiCall()
    }
}
