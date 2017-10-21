package org.x2b.studi.core.graphql.fetchers.mutation.getsecure;

import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.x2b.studi.core.graphql.fetchers.SecureRootFetcher;

public class SecureTestFetcher extends SecureRootFetcher {
    private static final Log log = LogFactory.getLog(SecureTestFetcher.class);

    @Override
    public void handleAuthenticationFailure(AuthenticationException e, DataFetchingEnvironment environment) {
        log.debug("auth failure!");
    }

    @Override
    public Object secureGet(DataFetchingEnvironment environment) {
        Subject s = SecurityUtils.getSubject();

        return s.isPermitted("foo:bar:read");
    }
}
