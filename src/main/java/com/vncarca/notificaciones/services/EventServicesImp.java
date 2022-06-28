/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:42:18
*/
package com.vncarca.notificaciones.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vncarca.arcasys.animal.services.AnimalService;
import com.vncarca.notificaciones.models.EventAlarm;
import com.vncarca.notificaciones.models.DTO.EventRequestBody;
import com.vncarca.notificaciones.models.DTO.ListUncheckEvents;
import com.vncarca.notificaciones.models.DTO.VacunaEventDTO;
import com.vncarca.notificaciones.repositories.AlarmRepository;

@Service
@Transactional
public class EventServicesImp implements VacunaEventService {
	@Autowired
	AlarmRepository alarmRepository;

	@Autowired
	AnimalService animalService;

	@Override
	@Transactional
	public VacunaEventDTO save(EventRequestBody alarm) {
		return getEventInfo(alarmRepository.save(new EventAlarm(alarm.getChecked(), alarm.getEventType(), alarm.getBody(),
				alarm.getEventDay(), animalService.findById(alarm.getPacienteId()))));
	}

	@Override
	public List<VacunaEventDTO> findAll() {
		return alarmRepository.findAll().stream().map(this::getEventInfo).collect(Collectors.toList());
	}

	@Override
	public Page<EventAlarm> findAll(Pageable pageable) {

		return alarmRepository.findAll(pageable);
	}

	@Override
	public void deleteById(Long id) {
		alarmRepository.deleteById(id);
	}

	@Override
	public VacunaEventDTO findById(Long id) {
		return alarmRepository.findById(id).map(this::getEventInfo).orElseThrow(() -> new NoSuchElementException(
				"Alarma con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public VacunaEventDTO checkAlarm(Long id) {
		Boolean checked = findById(id).getChecked();
		alarmRepository.checkAlarm(!checked, id);
		return findById(id);
	}

	@Override
	public List<VacunaEventDTO> findByPaciente(Long id) {
		return alarmRepository.findAllByPacienteId(id).stream().map(this::getEventInfo).collect(Collectors.toList());
	}

	@Override
	public List<VacunaEventDTO> findAllByOrderByCheckedAsc() {
		return alarmRepository.findAllByOrderByCheckedAsc().stream().map(this::getEventInfo).collect(Collectors.toList());
	}

	@Override
	public List<VacunaEventDTO> findAllByCheckedIsFalse() {
		return alarmRepository.findAllByCheckedIsFalse().stream().map(this::getEventInfo).collect(Collectors.toList());
	}

	@Override
	public List<ListUncheckEvents> findAllByUnchecked() {
		return alarmRepository.findAllByCheckedIsFalse().stream().map(this::getUnckekedEventList)
				.collect(Collectors.toList());
	}

	@Override
	public ListUncheckEvents findAllByUncheckedAndCurrentDate() {
		return new ListUncheckEvents(alarmRepository.countByCheckedIsFalseAndEventDayIsCurrentDate(),
				alarmRepository.findAllByCheckedIsFalseAndEventDayIsCurrentDate().stream().map(this::getEventInfo)
						.collect(Collectors.toList()));
	}

	private VacunaEventDTO getEventInfo(EventAlarm entity) {
		return new VacunaEventDTO(
				entity.getId(),
				entity.getEventDay(),
				entity.getChecked(),
				entity.getBody(),
				entity.getEventType(),
				animalService.getinfoDTO(entity.getPaciente()));
	}

	private ListUncheckEvents getUnckekedEventList(EventAlarm entity) {
		return new ListUncheckEvents(alarmRepository.countByCheckedIsFalse(),
				List.of(entity).stream().map(this::getEventInfo).collect(Collectors.toList()));
	}

	// private ListUncheckEvents getUnckekedEventListIsToday(EventAlarm entity) {
	// return new
	// ListUncheckEvents(alarmRepository.countByCheckedIsFalseAndEventDayIsCurrentDate(),
	// List.of(entity).stream().map(this::getEventInfo).collect(Collectors.toList()));
	// }
}
