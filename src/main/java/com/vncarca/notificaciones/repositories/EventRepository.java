/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:42:48
*/
package com.vncarca.notificaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vncarca.notificaciones.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
