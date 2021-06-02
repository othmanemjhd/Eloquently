package com.eloquently.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eloquently.model.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

public class JwtTokenProvider {
    public static final String SECRET = UUID.randomUUID().toString().replace("-", "");
    public static final long EXPIRATION_TIME = 31536000000L;
    private static final Logger logger= LoggerFactory.getLogger(JwtTokenProvider.class);


    public String generateTokenWithPrinciple(UserPrincipal userPrincipal) {
        return generateToken(userPrincipal);
    }

    public String generateToken(UserPrincipal userPrincipal) {
        return JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
    }

    public String getUserNameFromJWT(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET.getBytes())).build().verify(authToken);
            return true;
        } catch (JWTVerificationException ex) {
            logger.error(ex.getLocalizedMessage());
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
