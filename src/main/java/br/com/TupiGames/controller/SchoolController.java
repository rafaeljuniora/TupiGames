package br.com.TupiGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/escola")
public class SchoolController {

    @GetMapping("/home")
    public String homeSchool(){
        return "HomePages/SchoolHome";
    }

}
