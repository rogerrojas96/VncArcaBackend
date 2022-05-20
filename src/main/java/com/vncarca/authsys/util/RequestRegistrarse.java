package com.vncarca.authsys.util;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RequestRegistrarse {
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;       
}
