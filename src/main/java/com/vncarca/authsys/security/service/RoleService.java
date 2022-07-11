package com.vncarca.authsys.security.service;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.authsys.security.model.Rol;
import com.vncarca.authsys.security.model.RolDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService extends GlobalService<RolDto, Rol> {
	RolDto save(RolDto role);
	
	List<RolDto> findAll();
	
	Page<RolDto> findAll(Pageable pageable);
	
	RolDto findById(Long id);
	
	void delete(Long id);
	
}
