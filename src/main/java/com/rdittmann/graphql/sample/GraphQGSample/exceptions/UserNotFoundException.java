package com.rdittmann.graphql.sample.GraphQGSample.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserNotFoundException extends RuntimeException implements GraphQLError {
    private final Map<String, Object> extensions = new HashMap<>();

    public UserNotFoundException(final String message, final String surname) {
        super(message);
        extensions.put("surname", surname);
    }

    public UserNotFoundException(final String message, final Long userId) {
        super(message);
        extensions.put("userId", userId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
