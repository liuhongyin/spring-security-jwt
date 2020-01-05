package com.liuhongyin.springsecurity.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author marcot
 * @since 1/5/20
 */
@Component
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
        Date validity;
        if (rememberMe) {
            validity = new Date(now + tokenValidityInSeconds);
        } else {
            validity = new Date(now + tokenValidityInSecondsForRememberMe);
        }
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();
    }

}
