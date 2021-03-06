package com.vncarca.authsys.security.conf;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vncarca.authsys.security.service.CustomUserDetailsServiceImpl;
import com.vncarca.authsys.security.service.TokenProvider;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	@Value("${vncarca.app.jwtCookieName}")
	private String JWT_COOKIE_NAME;

	@Value("${jwt.header.string}")
	public String HEADER_STRING;

	@Value("${jwt.token.prefix}")
	public String TOKEN_PREFIX;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(httpServletRequest);
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				String username = tokenProvider.getUsernameFromToken(jwt);
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
				if (!userDetails.isEnabled()) {
					throw new DisabledException("Tu cuenta ha sido deshabilitada");
				}
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			httpServletRequest.setAttribute(e.getClass().getCanonicalName(), e.getMessage());
			logger.error("Error, token: {}", e.getMessage());
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HEADER_STRING);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX + " ")) {
			String accessToken = bearerToken.substring(7, bearerToken.length());
			if (accessToken == null)
				return null;
			return accessToken;
		}
		return null;
	}
}
