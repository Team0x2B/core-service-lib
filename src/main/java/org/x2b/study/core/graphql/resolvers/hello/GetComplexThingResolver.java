package org.x2b.study.core.graphql.resolvers.hello;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.x2b.study.core.graphql.types.Hello;

public class GetComplexThingResolver implements GraphQLResolver<Hello> {

    public String getComplexThing(Hello obj, String value) {
        return value + " is a complex thing";
    }

    public String value(Hello obj) {
        return "lmao";
    }
}
