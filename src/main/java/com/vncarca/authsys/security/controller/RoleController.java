package com.vncarca.authsys.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vncarca.authsys.security.service.RoleService;

import io.swagger.annotations.Api;
@Api(tags = "Roles", description = "Controlador para CRUD de Roles")
@RequestMapping("/roles")
@RestController
public class RoleController {

	@Autowired
	RoleService roleService;

}
