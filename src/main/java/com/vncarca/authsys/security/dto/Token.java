package com.vncarca.authsys.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class Token {
    private TokenType tokenType;
    private String tokenValue;
    private Long duration;
    private LocalDateTime expiryDate;

    public enum TokenType {
        ACCESS
    }
}