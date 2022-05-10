package com.vncarca.authsys.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseUsuarioInfo {
    private Long id;
	private String username;
	private String email;
	private List<String> roles;
}
