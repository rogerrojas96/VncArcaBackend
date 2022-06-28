/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 17:22:04
*/
package com.vncarca.notificaciones.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("app.websocket")
public class WebSocketProperties {
	/**
	 * Prefijo utilizado para las asignaciones de destino de WebSocket
	 */
	private String applicationPrefix = "/topic";
	/**
	 * Prefijo utilizado por los temas
	 */
	private String topicPrefix = "/topic";
	/**
	 * Endpoint que se puede utilizar para conectarse a
	 */
	private String endpoint = "/live";
	/**
	 * Orígenes permitidos
	 */
	private String allowedOrigins = "/api/live/*";
}
