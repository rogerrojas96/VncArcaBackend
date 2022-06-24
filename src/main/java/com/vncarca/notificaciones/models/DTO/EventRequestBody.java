/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 17:02:18
*/
package com.vncarca.notificaciones.models.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestBody {
	private String eventType;
	private Date eventDay;
	private Boolean checked;
	private String body;
	private Long pacienteId;
}
