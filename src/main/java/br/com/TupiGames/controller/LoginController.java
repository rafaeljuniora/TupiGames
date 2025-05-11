package br.com.TupiGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/escola")
    public String loginSchool(){
        return "LoginPages/SchoolLogin";
    }

    @GetMapping("/aluno")
    public String loginStudent(){
        return "LoginPages/StudentLogin";
    }

    @PostMapping("/escola/auth")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String senha,
                              Model model) {
        return "redirect:/home";
    }

}
