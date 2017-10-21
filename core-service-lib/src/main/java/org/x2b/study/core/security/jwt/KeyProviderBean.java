package org.x2b.study.core.security.jwt;

import org.apache.shiro.codec.Base64;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class KeyProviderBean {

    @Value("${auth.rsa.public-key}")
    private String encodedPublicKey;

    @Value("${auth.rsa.private-key}")
    private String encodedPrivateKey;

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;


    @PostConstruct //TODO: I hate this
    private void setUpKeys() throws IOException, GeneralSecurityException {
        KeyFactory rsaKeyFactory = KeyFactory.getInstance("RSA");
        publicKey = getPublicKey(encodedPublicKey, rsaKeyFactory);
        privateKey = getPrivateKey(encodedPrivateKey, rsaKeyFactory);
        System.out.println(publicKey.getAlgorithm());
        System.out.println(privateKey.getAlgorithm());
    }

    private RSAPublicKey getPublicKey(final String publicKeyStr, final KeyFactory keyFactory)
            throws IOException, InvalidKeySpecException, CertificateException, NoSuchProviderException {
        if (publicKeyStr == null) {
            return null;
        }
        CertificateFactory cf = CertificateFactory.getInstance("X509", "BC");
        try (ByteArrayInputStream bis = new ByteArrayInputStream(publicKeyStr.getBytes())) {
            X509Certificate cert = (X509Certificate) cf.generateCertificate(bis);
            return (RSAPublicKey) cert.getPublicKey();
        }
    }

    private RSAPrivateKey getPrivateKey(final String privateKeyStr, final KeyFactory keyFactory)
            throws IOException, InvalidKeySpecException, CertificateException, NoSuchProviderException {
        if (privateKeyStr == null) {
            return null;
        }
        return null;
    }

    private byte[] getKeyBytes(final String key) throws UnsupportedEncodingException {
        return Base64.decode(key.getBytes("utf-8"));
    }

    /**
     * Get this provider's public key
     * @return null iff a public key is not defined
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Get this providers private key
     * @return null iff a private key is not defined
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

}
