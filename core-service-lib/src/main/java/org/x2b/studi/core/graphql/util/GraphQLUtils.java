package org.x2b.studi.core.graphql.util;

import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;

public final class GraphQLUtils {

    private GraphQLUtils() {}

    /**
     * @param name The header name
     * @param environment The DataFetchingEnvironment
     * @return The value of the header or <code>null</code> if the http request does not exist
     */
    public static String getHeader(String name, DataFetchingEnvironment environment) {
        GraphQLContext context = environment.getContext();
        if (context.getRequest().isPresent()) {
            return context.getRequest().get().getHeader(name);
        }
        return null;
    }
}
