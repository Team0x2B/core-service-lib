package org.x2b.study.core.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MutationResolver implements GraphQLMutationResolver{

    private static final Log log = LogFactory.getLog(MutationResolver.class);

    public String logAString(String str) {
        log.info(str);
        return "done";
    }
}
