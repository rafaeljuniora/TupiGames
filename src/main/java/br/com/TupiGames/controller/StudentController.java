package br.com.TupiGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aluno")
public class StudentController {

    @GetMapping("/home")
    public String homeStudent(){
        return "HomePages/StudentHome";
    }
}