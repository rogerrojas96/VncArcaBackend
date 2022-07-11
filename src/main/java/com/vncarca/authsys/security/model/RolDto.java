package com.vncarca.authsys.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 04/07/2022
 * Time: 18:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDto implements Serializable {
    private Long id;
    @NotNull
    @NotBlank
    private String nombre;
}
