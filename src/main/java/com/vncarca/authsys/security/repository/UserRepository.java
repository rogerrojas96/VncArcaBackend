package com.vncarca.authsys.security.repository;

import com.vncarca.authsys.security.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByusername(String username);

	Page<Usuario> findByusername(Pageable pageable, String username);

	@Transactional()
	@Modifying
	@Query("update Usuario u set u.password = ?1 where u.id = ?2")
	public void patchPassword(String password, Long id);

	@Transactional()
	@Modifying
	@Query("update Usuario u set u.enabled = ?1 where u.id = ?2")
	public void setStatus(Boolean enabled, Long id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update  usuarios_roles  set usuario_id =?1, rol_id=?2  where usuario_id in (select ?3 from usuarios )", nativeQuery = true)
	void updateUsuarioRoles(Long usuario_id, Long rol_id, Long id);

	@Modifying(clearAutomatically = true)
	@Query("update Usuario u set u.username=?1 ,u.deleted=false where u.id = ?2")
	void updateProfile(String username, Long id);
}