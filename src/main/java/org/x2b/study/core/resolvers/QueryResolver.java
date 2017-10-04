package org.x2b.study.core.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.x2b.study.core.types.Hello;

public class QueryResolver implements GraphQLQueryResolver {
    public Hello hello(String foo) {
        return new Hello(foo);
    }
}
