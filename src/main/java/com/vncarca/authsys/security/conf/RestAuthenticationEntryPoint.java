package com.vncarca.authsys.security.conf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vncarca.authsys.security.exceptions.ErrorResponses;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String expiredJwtExceptionName = request.getAttribute(ExpiredJwtException.class.getCanonicalName()).toString();
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.FORBIDDEN, null, expiredJwtExceptionName);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(mapper.writeValueAsString(errorResponses));
        } catch (NullPointerException e) {
            logger.error("Error, auth: {}",authException.getMessage());
            ErrorResponses errorResponses = new ErrorResponses(HttpStatus.UNAUTHORIZED, null,
            "Para acceder a este recurso se requiere una autentificación completa");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(mapper.writeValueAsString(errorResponses));
        }

    }

}
