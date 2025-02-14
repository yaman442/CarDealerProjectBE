package com.binary.carDealerApp.classCarDealerApp.config;

import com.binary.carDealerApp.classCarDealerApp.entities.UserCredential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JWT (JSON Web Token) operations.
 * This class is responsible for generating JWTs for authenticated users.
 *
 * Key points:
 * 1. Uses @Service annotation to indicate it's a service component.
 * 2. Utilizes JwtConfigProperty for JWT configuration settings.
 * 3. Integrates with UserDetailsService for user information retrieval.
 * 4. Implements methods for token generation and secret key management.
 */
@Service
public class JwtService {

    @Autowired
    private JwtConfigProperty jwtConfigProperty;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Generates a JWT for a given username.
     *
     * @param username The username for which to generate the token.
     * @return A JWT string.
     */
    public String generateToken(String username) {
        UserCredential userCredential = (UserCredential) userDetailsService.loadUserByUsername(username);
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userCredential.getUsername());
        claims.put("role", userCredential.getRole());
        return generateToken(claims, userCredential);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserCredential userCredential) {
        final String username = extractUsername(token);
        return (username.equalsIgnoreCase(userCredential.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Private method to generate a JWT with claims and user credentials.
     *
     * @param claims The claims to be included in the JWT.
     * @param userCredential The user credentials.
     * @return A JWT string.
     */
    private String generateToken(Map<String, Object> claims, UserCredential userCredential) {
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtConfigProperty.getExpiration()))
                .signWith(getSecretKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Retrieves the secret key for JWT signing.
     *
     * @return A SecretKey object for JWT signing.
     */
    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperty.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}