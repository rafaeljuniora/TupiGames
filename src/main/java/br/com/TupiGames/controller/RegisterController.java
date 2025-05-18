package br.com.TupiGames.controller;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.dto.EscolaDTO;
import br.com.TupiGames.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    SchoolService schoolService;

    @GetMapping("/escola")
    public String showRegistrationForm(Model model) {
        model.addAttribute("escola", new EscolaDTO());
        return "RegisterPages/SchoolRegister";
    }

    @PostMapping("/escola/save")
    public String completeRegister(@ModelAttribute("escola") EscolaDTO escolaDTO) {
        Escola escola = escolaDTO.toEscola();
        schoolService.registerSchool(escola);
        return "redirect:/HomePages/SchoolHome";
    }
}