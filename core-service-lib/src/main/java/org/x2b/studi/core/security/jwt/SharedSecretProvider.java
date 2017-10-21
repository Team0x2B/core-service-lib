package org.x2b.studi.core.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * Reads the shared secret from the proper location
 */
//TODO: not sure this really needs to be it's own class
@Component
public class SharedSecretProvider {

    @Value("${studi.security.auth.secret}")
    private String encodedKey;

    public byte[] getKey() {
        return Base64.getDecoder().decode(encodedKey); //I think this won't be a performance problem
    }
}
