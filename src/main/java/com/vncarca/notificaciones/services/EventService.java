/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:41:43
*/
package com.vncarca.notificaciones.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.notificaciones.models.Event;

public interface EventService {
	public Event save(Event event);

	public List<Event> findAll();

	public Page<Event> findAll(Pageable pageable);

	public void deleteById(Long id);

	public Event findById(Long id);

}
