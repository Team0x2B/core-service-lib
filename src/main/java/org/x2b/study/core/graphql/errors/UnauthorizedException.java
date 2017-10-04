package org.x2b.study.core.graphql.errors;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnauthorizedException extends RuntimeException implements GraphQLError {


    public UnauthorizedException() {
        super("unauthorized");
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("reason", "unauthorized");
        return attributes;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
