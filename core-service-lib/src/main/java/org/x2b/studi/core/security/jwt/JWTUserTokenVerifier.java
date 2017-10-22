package org.x2b.studi.core.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.x2b.studi.core.security.User;
import org.x2b.studi.core.ServiceConstants;
import org.x2b.studi.core.security.shiro.JWTAuthenticationToken;

import java.util.UUID;

@Component
public class JWTUserTokenVerifier {

    private final JWTVerifier verifier;

    @Autowired
    public JWTUserTokenVerifier(SharedSecretProvider authSecretProvider) {

        Algorithm algorithm = Algorithm.HMAC256(authSecretProvider.getKey());
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
