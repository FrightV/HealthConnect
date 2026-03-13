package com.Project.HealthConnect.DTO;

import lombok.Data;

@Data
public class PacienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;


}