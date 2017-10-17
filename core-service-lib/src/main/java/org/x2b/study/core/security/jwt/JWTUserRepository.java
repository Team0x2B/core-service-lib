package org.x2b.study.core.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.x2b.study.core.ServiceConstants;
import org.x2b.study.core.security.User;
import org.x2b.study.core.security.shiro.JWTAuthenticationToken;
import sun.security.rsa.RSAPublicKeyImpl;

import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

public class JWTUserRepository {

    private final JWTVerifier verifier;

    public JWTUserRepository() {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(ServiceConstants.DO_NOT_USE_THIS_IN_PRODUCTION);
            //TODO: Use RSA here. DON'T USE THIS IN PRODUCTION
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); //TODO: This should crash at startup
        }
        verifier = JWT.require(algorithm)
                .withIssuer(ServiceConstants.SECURITY_TOKEN_ISSUER)
                .build();
    }

    public User getUser(JWTAuthenticationToken token) {
        //TODO: Eventually this should use getSubject to get a JSON and then deserialize into java
        DecodedJWT decodedJWT = verifier.verify(token.getToken());
        Claim uuidClaim = decodedJWT.getClaim(ServiceConstants.SECURITY_UUID_CLAIM);
        String uuidString = uuidClaim.asString();
        UUID uuid = UUID.fromString(uuidString);
        return () -> uuid; //TODO: this is not a good place for a lambda
    }
}
