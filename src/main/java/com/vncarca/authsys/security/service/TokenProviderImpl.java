package com.vncarca.authsys.security.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.vncarca.authsys.security.dto.Token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenProviderImpl implements TokenProvider {

    @Value("${vncarca.app.jwtSecret}")
    private  String JWT_SECRET;

    @Value("${vncarca.app.jwtExpirationMs}")
    private Long JWT_EXPIRATION;

    @Value("${jwt.authorities.key}")
    public  String AUTHORITIES_KEY;

    @Override
    public Token generateAccessToken(String subject,List<String> authorities) {
        Date now = new Date();
        Long duration = now.getTime() + JWT_EXPIRATION;
        Date expiryDate = new Date(duration);

        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now).claim(AUTHORITIES_KEY, authorities)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return new Token(Token.TokenType.ACCESS, token, duration,
                LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
    }

    @Override
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    public LocalDateTime getExpiryDateFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
    }

    @Override
    public boolean validateToken(String token) {
        try {

            Jwts.parser().setSigningKey(JWT_SECRET).parse(token);
            return true;
        } catch (SignatureException ex) {
            ex.printStackTrace();
            System.out.println("SignatureException");
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
            System.out.println("MalformedJwtException");
        } catch (ExpiredJwtException ex) {
             throw new ExpiredJwtException(null, null, "JWT Expirado", ex.getCause());
        } catch (UnsupportedJwtException ex) {
            ex.printStackTrace();
            System.out.println("UnsupportedJwtException");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("No se puede obtener el token JWT");
        }
        return false;
    }

}
