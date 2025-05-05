package br.com.TupiGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login/escola")
    public String loginEscola(){
        return "LoginPages/SchoolLogin";
    }
}
