/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 17:21:39
*/
package com.vncarca.notificaciones.web;

import com.vncarca.authsys.security.service.CustomUserDetailsServiceImpl;
import com.vncarca.authsys.security.service.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(WebSocketProperties.class)
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@ComponentScan("com.vncarca.notificaciones.controllers")
@Slf4j
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	private WebSocketProperties properties;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsService;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker(properties.getTopicPrefix());
		registry.setApplicationDestinationPrefixes(properties.getApplicationPrefix());
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(properties.getEndpoint()).setAllowedOriginPatterns("*")
				.addInterceptors(new HandshakeInterceptor() {
					@Override
					public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
							WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
						if (request instanceof ServletServerHttpRequest) {
							ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
							HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
							String token = httpServletRequest.getParameter("Authorization");
							if (null == token) {
								httpServletRequest.setAttribute("Authsorization", "xxxxx");
							}
							System.out.println("Handshake interceptor before == > " + token);
						}
						return true;
					}

					@Override
					public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
							@Nullable Exception exception) {
						System.out.println("Handshake interceptor after");
					}
				}).withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {

				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
					List<String> authorization = accessor.getNativeHeader("Authorization");
					log.info("Authorization: {}", authorization);

					UserDetails userDetails = customUserDetailsService
							.loadUserByUsername(tokenProvider.getUsernameFromToken(getJwtFromRequest(authorization)));

					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(authentication);

					accessor.setUser(authentication);
				}

				return message;
			}
		});
	}

	private String getJwtFromRequest(List<String> requestToken) {
		String bearerToken = requestToken.get(0);
		return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer" + " ")
				? bearerToken.substring(7, bearerToken.length())
				: null;
	}
}
