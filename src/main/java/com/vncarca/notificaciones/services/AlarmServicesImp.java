/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:42:18
*/
package com.vncarca.notificaciones.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vncarca.notificaciones.models.Alarm;
import com.vncarca.notificaciones.repositories.AlarmRepository;

@Service
public class AlarmServicesImp implements AlarmService {
	@Autowired
	AlarmRepository alarmRepository;

	@Override
	public Alarm save(Alarm alarm) {

		return alarmRepository.save(alarm);
	}

	@Override
	public List<Alarm> findAll() {

		return alarmRepository.findAll();
	}

	@Override
	public Page<Alarm> findAll(Pageable pageable) {

		return alarmRepository.findAll(pageable);
	}

	@Override
	public void deleteById(Long id) {
		alarmRepository.deleteById(id);
	}

	@Override
	public Alarm findById(Long id) {
		return alarmRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				"Alarma con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public Alarm checkAlarm(Long id) {
		Boolean checked = findById(id).getChecked();
		return alarmRepository.checkAlarm(!checked, id);
	}

}
