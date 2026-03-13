package com.Project.HealthConnect.service;

import com.Project.HealthConnect.DTO.PacienteDTO;
import com.Project.HealthConnect.model.Paciente;
import com.Project.HealthConnect.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public void salvar(PacienteDTO dto) {

        Paciente paciente = new Paciente();

        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());
        paciente.setEmail(dto.getEmail());

        pacienteRepository.save(paciente);
    }

    public PacienteDTO buscarPorId(Long id) {

        Paciente paciente = pacienteRepository.findById(id)
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

        return dto;
    }

    public void excluir(Long id) {
        pacienteRepository.deleteById(id);
    }
}