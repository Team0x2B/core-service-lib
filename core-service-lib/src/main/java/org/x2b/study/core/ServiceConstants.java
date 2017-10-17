package org.x2b.study.core;

public final class ServiceConstants {

    private ServiceConstants() {}


    public static final String SECURITY_TOKEN_ISSUER = "studi_auth_service";
    public static final String SECURITY_UUID_CLAIM = "uuid";
    public static final String SECURITY_AUTHENTICATION_REALM_NAME = "generic_authentication_realm";

    public static final String HTTP_AUTH_HEADER = "authorization";

    public static final String DO_NOT_USE_THIS_IN_PRODUCTION = "secret";
}