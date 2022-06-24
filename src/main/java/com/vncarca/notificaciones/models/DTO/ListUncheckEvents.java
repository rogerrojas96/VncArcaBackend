/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 15:47:04
*/
package com.vncarca.notificaciones.models.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListUncheckEvents {
	private Long total;
	private List<VacunaEventDTO> events;
}
