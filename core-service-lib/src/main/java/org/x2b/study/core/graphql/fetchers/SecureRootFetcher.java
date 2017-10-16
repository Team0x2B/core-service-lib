package org.x2b.study.core.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.x2b.study.core.graphql.util.GraphQLUtils;
import org.x2b.study.core.security.shiro.JWTAuthenticationToken;

import java.util.Map;

/**
 * Provides a secure edge for GraphQL queries. User this for all root query types to ensure that the user is properly
 * logged in
 * @param <T>
 */
public abstract class SecureRootFetcher<T> implements DataFetcher<T> {
    @Override
    public T get(DataFetchingEnvironment environment) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            AuthenticationToken token = new JWTAuthenticationToken(getAuthTokenFromContext(environment));
            try {
                currentUser.login(token);
            } catch (AuthenticationException e) {
                handleAuthenticationFailure(e, environment);
            }
        }
        return secureGet(environment);
    }


    private String getAuthTokenFromContext(DataFetchingEnvironment environment) {
        return GraphQLUtils.getHeader("authorization", environment); //TODO: make this
    }

    public abstract void handleAuthenticationFailure(AuthenticationException e, DataFetchingEnvironment environment);

    public abstract T secureGet(DataFetchingEnvironment environment);
}
