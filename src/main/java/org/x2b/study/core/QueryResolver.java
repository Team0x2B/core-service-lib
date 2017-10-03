package org.x2b.study.core;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class QueryResolver implements GraphQLQueryResolver {
    public Hello hello() {
        return new Hello();
    }
}
