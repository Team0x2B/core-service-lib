package org.x2b.studi.core;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;

public class QueryResolver implements GraphQLQueryResolver {
    public Hello hello() {
        return new Hello();
    }
}
