package com.vncarca.authsys.security.conf;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vncarca.authsys.security.exceptions.ErrorResponses;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	String message[] = new String[1];

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<String> names = Collections.list(request.getAttributeNames());
			names
					.retainAll(List.of(ExpiredJwtException.class.getCanonicalName(), DisabledException.class.getCanonicalName()));
			names.forEach(e -> message[0] = request.getAttribute(e).toString());
			log.error("Error Message: {}", message[0].toString());
			ErrorResponses errorResponses = new ErrorResponses(HttpStatus.FORBIDDEN, null, message[0]);
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().write(mapper.writeValueAsString(errorResponses));

		} catch (NullPointerException e) {
			log.error("NullPointerException: {}", authException.getMessage());
			ErrorResponses errorResponses = new ErrorResponses(HttpStatus.UNAUTHORIZED, null,
					"Para acceder a este recurso se requiere una autentificación completa");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(mapper.writeValueAsString(errorResponses));
		}
	}
}
