package br.com.TupiGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atividades")
public class ActivityController {
    @GetMapping("/professores/criar")
    public String createActivity(){
        return "ActivityPages/TeacherCreateActivity";
    }
}
