package com.liuhongyin.springsecurity.util;

import com.sun.org.apache.xerces.internal.xs.StringList;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author marcot
 * @since 1/5/20
 */
@Component
@Slf4j
public class JwtTokenUtil {


    @Value("${jwt.base64-secret}")
    private String base64Secret;
    @Value("${jwt.token-validity-in-seconds}")
    private long tokenValidityInSeconds;
    @Value("${jwt.token-validity-in-seconds-for-remember-me}")
    private long tokenValidityInSecondsForRememberMe;


    private static final String AUTHORITIES_KEY = "authorities";


    /**
     * @param authentication
     * @param rememberMe
     * @return token
     */
    public String generateToken(Authentication authentication, Boolean rememberMe) {

        List<String> authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        ;
        long now = (new Date()).getTime();
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        Date validity;
        if (rememberMe) {
            validity = new Date(now + tokenValidityInSeconds);
        } else {
            validity = new Date(now + tokenValidityInSecondsForRememberMe);
        }


        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                ((List<String>)claims.get(AUTHORITIES_KEY)).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

}
