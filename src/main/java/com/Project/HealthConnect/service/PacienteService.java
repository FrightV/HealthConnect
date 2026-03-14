package com.Project.HealthConnect.service;

import com.Project.HealthConnect.DTO.PacienteDTO;
import com.Project.HealthConnect.model.Paciente;
import com.Project.HealthConnect.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import br.com.caelum.stella.validation.CPFValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository PacienteRepository;

    public PacienteService(PacienteRepository PacienteRepository) {
        this.PacienteRepository = PacienteRepository;
    }

    public List<PacienteDTO> listarTodos() {
        return PacienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public void validarCpf(String cpf){
        CPFValidator validator=new CPFValidator();

        try{
            validator.assertValid(cpf);
        }catch(Exception e){
            throw new RuntimeException("CPF inválido");
        }
    }

    public void salvar(PacienteDTO dto) {

        Paciente paciente = new Paciente();

        paciente.setNome(dto.getNome());

        paciente.setCpf(dto.getCpf());
        validarCpf(dto.getCpf());
        if(PacienteRepository.existsByCpf(dto.getCpf())){
            throw new RuntimeException("CPF já cadastrado");
        }

        paciente.setTelefone(dto.getTelefone());
        paciente.setEmail(dto.getEmail());
        paciente.setCep(dto.getCep());
        paciente.setLogradouro(dto.getLogradouro());
        paciente.setNumero(dto.getNumero());
        paciente.setBairro(dto.getBairro());
        paciente.setCidade(dto.getCidade());
        paciente.setEstado(dto.getEstado());


        PacienteRepository.save(paciente);
    }

    public PacienteDTO buscarPorId(Long id) {

        Paciente paciente = PacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        return converterParaDTO(paciente);
    }

    private PacienteDTO converterParaDTO(Paciente paciente) {

        PacienteDTO dto = new PacienteDTO();

        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setCpf(paciente.getCpf());
        dto.setTelefone(paciente.getTelefone());
        dto.setEmail(paciente.getEmail());
        paciente.setCep(dto.getCep());
        paciente.setLogradouro(dto.getLogradouro());
        paciente.setNumero(dto.getNumero());
        paciente.setBairro(dto.getBairro());
        paciente.setCidade(dto.getCidade());
        paciente.setEstado(dto.getEstado());


        return dto;
    }

    public void excluir(Long id) {
        PacienteRepository.deleteById(id);
    }

    public void atualizarContato(PacienteDTO dto) {

        Paciente paciente = PacienteRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setTelefone(dto.getTelefone());
        paciente.setEmail(dto.getEmail());

        PacienteRepository.save(paciente);
    }
}