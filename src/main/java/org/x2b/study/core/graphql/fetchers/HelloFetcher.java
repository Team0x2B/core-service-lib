package org.x2b.study.core.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.x2b.study.core.graphql.types.Hello;

public class HelloFetcher implements DataFetcher<Hello> {
    @Override
    public Hello get(DataFetchingEnvironment environment) {
        String foo = environment.getArgument("foo");
        return new Hello(foo);
    }
}
