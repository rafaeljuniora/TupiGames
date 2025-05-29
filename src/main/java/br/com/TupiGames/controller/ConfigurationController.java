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
    public String loginSchoola() {
        return "ConfigurationPages/StudentConfiguration";
    }

    @GetMapping("/professor")
    public String loginProfessor() { return "ConfigurationPages/TeacherConfiguration";
    }


}
