package com.Project.HealthConnect.controller;

import com.Project.HealthConnect.DTO.PacienteDTO;
import com.Project.HealthConnect.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public String listarPacientes(Model model) {

        model.addAttribute("pacientes", pacienteService.listarTodos());

        return "pacientes/list";
    }

    @GetMapping("/novo")
    public String novoPaciente(Model model) {

        model.addAttribute("paciente", new PacienteDTO());

        return "pacientes/form";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute PacienteDTO pacienteDTO) {

        pacienteService.salvar(pacienteDTO);

        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {

        PacienteDTO paciente = pacienteService.buscarPorId(id);

        model.addAttribute("paciente", paciente);

        return "pacientes/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id){

        pacienteService.excluir(id);
        return "redirect:/pacientes";
    }
}