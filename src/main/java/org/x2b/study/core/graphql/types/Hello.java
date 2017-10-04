package org.x2b.study.core.graphql.types;

import org.x2b.study.core.graphql.errors.UnauthorizedException;

import java.util.concurrent.CompletableFuture;

public class Hello {

    private String value;

    public Hello(String foo) {
        CompletableFuture<String> whatever;
        if (foo != null) {
            System.out.println(foo); //TODO: don't system.out in production
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
