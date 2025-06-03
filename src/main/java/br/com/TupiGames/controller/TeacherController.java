package br.com.TupiGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/professor")
public class TeacherController {
    @GetMapping("/home")
    public String homeSchool(){
        return "HomePages/TeacherHome";
    }
}
