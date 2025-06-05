package br.com.TupiGames.controller;

import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/configuration")
public class ConfigurationController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/aluno")
    public String configAluno() {
        return "ConfigurationPages/StudentConfiguration";
    }

    @GetMapping("/professor")
    public String configProfessor(Model model) {
        try {
            model.addAttribute("professor", new Professor());
        } catch (Exception e) {
            model.addAttribute("professor", new Professor());
        }

        return "ConfigurationPages/TeacherConfiguration";
    }
}