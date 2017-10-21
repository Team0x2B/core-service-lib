package org.x2b.study.core.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.x2b.study.core.ServiceConstants;
import org.x2b.study.core.security.User;
import org.x2b.study.core.security.shiro.JWTAuthenticationToken;
import sun.security.rsa.RSAPublicKeyImpl;

import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Component
public class JWTUserTokenVerifier {

    private final JWTVerifier verifier;

    @Autowired
    public JWTUserTokenVerifier(KeyProviderBean rsaKeyProvider) {
        RSAPublicKey publicKey = rsaKeyProvider.getPublicKey();
        RSAPrivateKey privateKey = rsaKeyProvider.getPrivateKey();
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        verifier = JWT.require(algorithm)
                .withIssuer(ServiceConstants.SECURITY_TOKEN_ISSUER)
                .build();
    }

    public User getUser(JWTAuthenticationToken token) {
        //TODO: Eventually this should use getSubject to get a JSON and then deserialize into java
//        DecodedJWT decodedJWT = verifier.verify(token.getToken());
//        Claim uuidClaim = decodedJWT.getClaim(ServiceConstants.SECURITY_UUID_CLAIM);
//        String uuidString = uuidClaim.asString();
//        UUID uuid = UUID.fromString(uuidString);

        return () -> UUID.fromString(token.getToken()); //TODO: this is not a good place for a lambda
    }
}
