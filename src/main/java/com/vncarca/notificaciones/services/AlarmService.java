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

import com.vncarca.notificaciones.models.Alarm;

public interface AlarmService {
	public Alarm save(Alarm alarm);

	public List<Alarm> findAll();

	public Page<Alarm> findAll(Pageable pageable);

	public void deleteById(Long id);

	public Alarm findById(Long id);

	public Alarm checkAlarm(Long id);
}
