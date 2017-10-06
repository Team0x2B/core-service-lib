package org.x2b.study.core.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLScalarType;

public class CreateEmptyUserFetcher implements DataFetcher<String> {
    @Override
    public String get(DataFetchingEnvironment environment) {
        return "akjsdhakjsd";
    }
}
