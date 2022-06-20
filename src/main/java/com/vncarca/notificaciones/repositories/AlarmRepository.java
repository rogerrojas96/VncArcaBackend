/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:43:04
*/
package com.vncarca.notificaciones.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vncarca.notificaciones.models.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

	@Transactional()
	@Modifying
	@Query("update Alarm a set a.checked = ?1 where u.id = ?2")
	public Alarm checkAlarm(Boolean checked, Long id);
}
