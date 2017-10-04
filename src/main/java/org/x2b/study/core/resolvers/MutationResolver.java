package org.x2b.study.core.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

public class MutationResolver implements GraphQLMutationResolver{

    public String logAString(String str) {
        System.out.println(str);
        return "done";
    }
}
