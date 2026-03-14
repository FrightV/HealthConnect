package com.Project.HealthConnect.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PacienteDTO {

    private Long id;

    @NotBlank(message="Nome é obrigatório")
    @Size(min=2, message="Nome deve ter pelo menos 2 caracteres")
    private String nome;

    @NotBlank(message="CPF é obrigatório")
    private String cpf;

    @NotBlank(message="Telefone é obrigatório")
    @Pattern(regexp="\\d{10,11}",message="Telefone deve conter 10 ou 11 números")
    private String telefone;

    @NotBlank(message="Email é obrigatório")
    @Email(message="Email inválido")
    private String email;

    @NotBlank(message="CEP é obrigatório")
    @Pattern(regexp="\\d{8}",message="CEP deve conter 8 números")
    private String cep;

    @NotBlank(message="Número da casa é obrigatório")
    private String numero;

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;


}