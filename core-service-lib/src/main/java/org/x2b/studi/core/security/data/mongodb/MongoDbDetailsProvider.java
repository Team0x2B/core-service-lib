package org.x2b.studi.core.security.data.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.x2b.studi.core.ServiceConstants;

@Component
public class MongoDbDetailsProvider {

    @Value("${" + ServiceConstants.SECURITY_DATA_MONGODB_HOST_PROPERTY + ":localhost}")
    private String host;

    @Value("${" + ServiceConstants.SECURITY_DATA_MONGODB_PORT_PROPERTY + ":27017}")
    private int port;


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
