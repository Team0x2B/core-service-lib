package org.x2b.studi.core.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.x2b.studi.core.graphql.util.GraphQLUtils;
import org.x2b.studi.core.ServiceConstants;
import org.x2b.studi.core.security.shiro.JWTAuthenticationToken;

/**
 * Provides a secure edge for GraphQL queries. User this for all root query types to ensure that the user is properly
 * logged in
 * @param <T>
 */
public abstract class SecureRootFetcher<T> implements DataFetcher<T> {
    @Override
    public T get(DataFetchingEnvironment environment) {
        for (Realm realm : ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms())
            System.out.println(realm.getName());
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
        return GraphQLUtils.getHeader(ServiceConstants.HTTP_AUTH_HEADER, environment); //TODO: make this
    }

    public abstract void handleAuthenticationFailure(AuthenticationException e, DataFetchingEnvironment environment);

    public abstract T secureGet(DataFetchingEnvironment environment);
}