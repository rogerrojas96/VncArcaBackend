package com.vncarca.authsys.security.service;

import java.util.List;

import com.vncarca.arcasys.globalService.GlobalService;
import com.vncarca.authsys.security.model.RolDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vncarca.authsys.security.model.Rol;

public interface RoleService extends GlobalService<RolDto,Rol> {
	 RolDto save(RolDto role);

	 List<RolDto> findAll();

	 Page<RolDto> findAll(Pageable pageable);

	 RolDto findById(Long id);

	 void delete(Long id);

}
