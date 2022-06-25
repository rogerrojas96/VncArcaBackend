/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:42:02
*/
package com.vncarca.notificaciones.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.notificaciones.models.EventAlarm;
import com.vncarca.notificaciones.models.DTO.EventRequestBody;
import com.vncarca.notificaciones.models.DTO.ListUncheckEvents;
import com.vncarca.notificaciones.models.DTO.VacunaEventDTO;

public interface VacunaEventService {

	VacunaEventDTO save(EventRequestBody alarm);

	List<VacunaEventDTO> findAll();

	Page<EventAlarm> findAll(Pageable pageable);

	void deleteById(Long id);

	VacunaEventDTO findById(Long id);

	List<VacunaEventDTO> findByPaciente(Long id);

	List<VacunaEventDTO> findAllByOrderByCheckedAsc();

	List<VacunaEventDTO> findAllByCheckedIsFalse();

	List<ListUncheckEvents> findAllByUnchecked();

	ListUncheckEvents findAllByUncheckedAndCurrentDate();

	VacunaEventDTO checkAlarm(Long id);
}
