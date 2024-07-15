package br.com.postech.techchallenge.main.security;

import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;

public class CognitoJwtDecoder {

    private String COGNITO_JWKS_URL = "https://cognito-idp.us-east-1.amazonaws.com/us-east-1_pGFsCFVuS/.well-known/jwks.json";

    public RSAPublicKey getPublicKey(String kid) throws Exception {
        URL url = new URL(COGNITO_JWKS_URL);
        JWKSet jwkSet = JWKSet.load(url);
        RSAKey rsaKey = (RSAKey) jwkSet.getKeyByKeyId(kid);
        return rsaKey.toRSAPublicKey();
    }
    
    public String extractKid(String jwtToken) {
        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);
        }

        String[] parts = jwtToken.split("\\.");
        String base64EncodedHeader = parts[0];

        String headerJson = new String(Base64.getDecoder().decode(base64EncodedHeader));

        JsonObject headerObject = JsonParser.parseString(headerJson).getAsJsonObject();

        String kid = headerObject.get("kid").getAsString();

        return kid;
    }
}
