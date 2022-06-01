package com.vncarca.authsys.security.dto;

import com.vncarca.authsys.security.model.Usuario;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;

    public Usuario getUserFromDto(){
        Usuario user = new Usuario();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    } 
}
