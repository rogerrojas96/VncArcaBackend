package com.vncarca.authsys.security.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.vncarca.authsys.security.dto.Token;

import org.springframework.security.core.GrantedAuthority;


public interface TokenProvider {
    Token generateAccessToken(String subject,List<String> authorities);

    String getUsernameFromToken(String token);

    LocalDateTime getExpiryDateFromToken(String token);

    boolean validateToken(String token);
}
