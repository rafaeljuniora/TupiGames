package br.com.TupiGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/configuration")
public class ConfigurationController {

    @GetMapping("/aluno")
    public String configAluno() {
        return "ConfigurationPages/StudentConfiguration";
    }

    @GetMapping("/professor")
    public String configProfessor() {
        return "ConfigurationPages/TeacherConfiguration";
    }

    @GetMapping("/escola")
    public String configEscola() {
        return "ConfigurationPages/SchoolConfiguration";
    }
}