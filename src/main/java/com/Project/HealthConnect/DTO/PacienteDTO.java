package com.Project.HealthConnect.DTO;

import lombok.Data;

@Data
public class PacienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

}