package br.com.postech.techchallenge.main.security;

import java.security.interfaces.RSAPublicKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtHelper {

    private final RSAPublicKey publicKey;
    private final String token;

    public JwtHelper(String token) throws Exception {
    	this.token = token.replace("Bearer ", "");
        CognitoJwtDecoder jwtDecoder = new CognitoJwtDecoder();
        this.publicKey = jwtDecoder.getPublicKey(jwtDecoder.extractKid(this.token));
    }

    public Claims extractClaims() {
        return Jwts.parser()
                .setSigningKey(this.publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername() {
        return extractClaims().getSubject();
    }
}
