/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:41:55
*/
package com.vncarca.notificaciones.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vncarca.notificaciones.models.Event;
import com.vncarca.notificaciones.repositories.EventRepository;

@Service
public class EventServicesImp implements EventService {
	@Autowired
	EventRepository eventRepository;

	@Override
	public Event save(Event event) {

		return eventRepository.save(event);
	}

	@Override
	public List<Event> findAll() {

		return eventRepository.findAll();
	}

	@Override
	public Page<Event> findAll(Pageable pageable) {

		return eventRepository.findAll(pageable);
	}

	@Override
	public void deleteById(Long id) {
		eventRepository.deleteById(id);
	}

	@Override
	public Event findById(Long id) {

		return eventRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				"Eventos con ID: " + id.toString() + " no existe en el servidor"));
	}

}
