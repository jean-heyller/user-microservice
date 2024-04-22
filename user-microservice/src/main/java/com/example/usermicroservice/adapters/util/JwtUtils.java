package com.example.usermicroservice.adapters.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${security.jwt.private-key:")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String uSerGenerator;


    public String createToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String username = authentication.getPrincipal().toString();
        String auythorities = authentication.getAuthorities().stream().map
                (a -> a.getAuthority()).collect(Collectors.joining(","));

        String token = JWT.create()
                .withIssuer(this.uSerGenerator)
                .withSubject(username)
                .withClaim("authorities", auythorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 7200000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return token;

    }



        public DecodedJWT validateToken(String token){
            try{
                Algorithm algorithm  = Algorithm.HMAC256(this.privateKey);

                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(this.uSerGenerator)
                        .build();
                verifier.verify(token);

                DecodedJWT decodedJWT = JWT.decode(token);
                return decodedJWT;
            }catch (Exception e){
                throw new JWTVerificationException("Invalid token");
        }

    }

    public String getUserNameFromToken(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claim){
        return decodedJWT.getClaim(claim);
    }

    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
