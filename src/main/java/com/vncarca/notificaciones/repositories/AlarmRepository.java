/**
* Created by Roy Morocho.
* User: steve
* Date: 20 jun 2022
* Time: 13:43:04
*/
package com.vncarca.notificaciones.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vncarca.notificaciones.models.EventAlarm;

@Repository
public interface AlarmRepository extends JpaRepository<EventAlarm, Long> {

	@Transactional()
	@Modifying
	@Query("update EventAlarm a set a.checked = ?1 where a.id = ?2")
	void checkAlarm(Boolean checked, Long id);

	List<EventAlarm> findAllByPacienteId(Long id);

	List<EventAlarm> findAllByOrderByCheckedAsc();

	List<EventAlarm> findAllByCheckedIsFalse();

	Long countByCheckedIsFalse();

	@Query("SELECT count(*) FROM EventAlarm where checked=false and eventDay = current_date")
	Long countByCheckedIsFalseAndEventDayIsCurrentDate();

	@Query("SELECT e FROM EventAlarm e where checked=false and eventDay=current_date")
	List<EventAlarm> findAllByCheckedIsFalseAndEventDayIsCurrentDate();

}
