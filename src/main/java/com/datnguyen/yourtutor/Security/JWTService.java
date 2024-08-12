package com.datnguyen.yourtutor.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    // Secret Key
    private Key getSignInKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Extract all claims
    private Claims extractAllClaims(String jwtToken){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(jwtToken)
                .getBody();
    }

    // Extract 1 claim from the extracted claims
    public <T> T extractClaim(String jwtToken, Function<Claims, T>claimResolver){
        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    // Extract username
    public String extractUserName(String jwtToken){
        return extractClaim(jwtToken, Claims::getSubject); // The username is in subject part of the claim
    }

    // Token generate
    public String tokenGenerator(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                // Set token live
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *24)) // 24 hours
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Algorithm for 256-bit hash
                .compact(); // Generate and return token
    }
    // Overloading to make tokenGenerator easier to access (only UserDetails param)
    public String tokenGenerator(UserDetails userDetails){
        return tokenGenerator(new HashMap<>(), userDetails);
    }

    // Extract Expiration from specified claim
    public Date extractExpiration(String jwtToken){
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    // Check if token is expired
    public boolean isExpiredToken(String jwtToken){
        return (extractExpiration(jwtToken).before(new Date()));
    }

    // check if token is valid
    public boolean isTokenValidate(String jwtToken, UserDetails userDetails){
        String userName = extractUserName(jwtToken);
        return userName.equals(userDetails.getUsername()) && !isExpiredToken(jwtToken);
    }
}