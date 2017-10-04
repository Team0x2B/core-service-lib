package org.x2b.study.core.graphql.types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.x2b.study.core.graphql.errors.UnauthorizedException;

import java.util.concurrent.CompletableFuture;

public class Hello {

    private static final Log log = LogFactory.getLog(Hello.class);

    private String value;

    public Hello(String foo) {
        CompletableFuture<String> whatever;
        if (foo != null) {
            log.info(foo);
        }
        this.value = "rip";
    }


    public String getValue() {
        if (value.equals("rip")) {
            throw new UnauthorizedException();
        }
        return "just testing";
    }
}
