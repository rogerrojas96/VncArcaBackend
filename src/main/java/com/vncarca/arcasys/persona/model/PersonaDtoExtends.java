package com.vncarca.arcasys.persona.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Roy Morocho.
 * User: steve
 * Date: 05/07/2022
 * Time: 16:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDtoExtends extends PersonaDto implements Serializable {
    private Long id;
}
