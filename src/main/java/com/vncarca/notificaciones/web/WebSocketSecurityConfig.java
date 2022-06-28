/**
* Created by Roy Morocho.
* User: steve
* Date: 24 jun 2022
* Time: 11:25:11
*/
package com.vncarca.notificaciones.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

	@Override
	protected boolean sameOriginDisabled() {
		return true;
	}

	@Override
	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		messages.simpTypeMatchers(SimpMessageType.CONNECT, SimpMessageType.DISCONNECT, SimpMessageType.OTHER).permitAll()
				.nullDestMatcher().authenticated()
				.simpSubscribeDestMatchers("/araca/queue/notifications").permitAll()
				.simpDestMatchers("/topic/**").hasRole("VETERINARIO")
				.simpSubscribeDestMatchers("/topic/paciente/alarms/*").hasRole("VETERINARIO")
				.anyMessage().denyAll();
	}

}