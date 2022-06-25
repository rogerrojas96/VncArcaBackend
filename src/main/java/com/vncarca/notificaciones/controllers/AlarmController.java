/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:43:41
*/
package com.vncarca.notificaciones.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.vncarca.notificaciones.models.DTO.EventRequestBody;
import com.vncarca.notificaciones.models.DTO.ListUncheckEvents;
import com.vncarca.notificaciones.models.DTO.VacunaEventDTO;
import com.vncarca.notificaciones.services.VacunaEventService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class AlarmController {

	private VacunaEventService alarmService;

	@SubscribeMapping("/paciente/alarms/get")
	public List<VacunaEventDTO> findAll() {
		log.info("findAll");
		return alarmService.findAllByOrderByCheckedAsc();
	}

	@SubscribeMapping("/paciente/alarms/getUncheck")
	public List<VacunaEventDTO> findAllByCheckedIsFalse() {
		log.info("findAllByCheckedIsFalse");
		return alarmService.findAllByCheckedIsFalse();
	}

	@SubscribeMapping("/paciente/alarms/eventsUncheck")
	public List<ListUncheckEvents> findAllUncheckedEvent() {
		log.info("findAllUncheckedEvent");
		return alarmService.findAllByUnchecked();
	}

	@SubscribeMapping("/paciente/alarms/currentEvent")
	public ListUncheckEvents findAllByUncheckedAndCurrentDate() {
		log.info("findAllByUncheckedAndCurrentDate");
		return alarmService.findAllByUncheckedAndCurrentDate();
	}

	@MessageMapping("/paciente/alarms/create")
	@SendTo("/topic/paciente/alarms/created")
	public VacunaEventDTO save(@Valid @RequestBody EventRequestBody eventRequestBody) {
		try {
			System.out.println("SAVE ->" + eventRequestBody);
			return alarmService.save(eventRequestBody);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al guardar evento en el servidor", e) {
			};
		}
	}

	@MessageMapping("/paciente/alarms/checkEvent")
	@SendTo("/topic/paciente/alarms/checked")
	public VacunaEventDTO checkEvent(Long id) {
		try {
			System.out.println("Update id ->" + id);
			return alarmService.checkAlarm(id);
		} catch (DataAccessException e) {
			throw new DataAccessException("Error al actualizar evento en el servidor", e) {
			};
		}
	}

	@SubscribeMapping("/paciente/alarms/{id}/get")
	public VacunaEventDTO findById(@DestinationVariable Long id) {
		return alarmService.findById(id);
	}

	@SubscribeMapping("/paciente/alarm/{id}/alarms/get")
	public List<VacunaEventDTO> findalarmsByPaciente(@DestinationVariable("id") Long id) {
		return alarmService.findByPaciente(id);
	}

	// @MessageExceptionHandler
	// @SendToUser("/topic/error")
	// public String handleException(EventNotFoundException ex) {
	// log.debug("Event not found", ex);
	// return "The requested event was not found";
	// }

	// @MessageExceptionHandler
	// @SendToUser("/topic/error")
	// public String handleException(AlarmNotFoundException ex) {
	// log.debug("Alarm not found", ex);
	// return "The given alarm was not found";
	// }
}
