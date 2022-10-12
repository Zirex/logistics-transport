package com.ingeneo.logistics.utils;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JwtUtil {
    @Value("${jwt.secret:secretkey123}")
    private String secret;

    private Map<String, Claim> extractAllClaims(String token) {
        Map<String, Claim> claims = null;
        try {
            JWTVerifier verifierJwt = JWT.require(Algorithm.HMAC256(secret.getBytes())).build();
            DecodedJWT jwt = verifierJwt.verify(token);
            claims = jwt.getClaims();
        }catch(JWTDecodeException exception) {

        }
        return claims;
    }

    public String extractUsername(String token) {
        Claim claim = extractClaim(token, "sub");
        return claim.asString();
    }

    public Claim extractClaim(String token, String claimResolver) {
        final Map<String, Claim> claims = extractAllClaims(token);
        return claims.get(claimResolver);
    }

    public String createToken(Map<String, Object> claims, String subject, String uri) {
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(uri)
                .withPayload(claims)
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Date extractExpiration(String token) {
        Claim claim = extractClaim(token, "exp");
        return claim.asDate();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
