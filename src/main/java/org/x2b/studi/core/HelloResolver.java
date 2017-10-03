package org.x2b.studi.core;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;

public class HelloResolver implements GraphQLResolver<Hello> {

    private Hello hello;

    public HelloResolver(Hello hello) {
        this.hello = hello;
    }

    public String value() {
        return hello.getValue();
    }
}
