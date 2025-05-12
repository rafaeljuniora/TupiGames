package br.com.TupiGames.controller;

import br.com.TupiGames.dto.AlunoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/escola")
public class SchoolController {

    @GetMapping("/home")
    public String homeSchool(){
        return "HomePages/SchoolHome";
    }

    @GetMapping("/management")
    public String managementSchool(Model model){
        model.addAttribute("aluno", new AlunoDTO());
        return "ManagementPages/SchoolManagement";
    }

}
