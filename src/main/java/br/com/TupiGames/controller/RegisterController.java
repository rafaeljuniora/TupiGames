package br.com.TupiGames.controller;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.dto.AlunoDTO;
import br.com.TupiGames.dto.EscolaDTO;
import br.com.TupiGames.service.SchoolService;
import br.com.TupiGames.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    SchoolService schoolService;

    @Autowired
    StudentService studentService;

    @GetMapping("/escola")
    public String showRegistrationForm(Model model) {
        model.addAttribute("escola", new EscolaDTO());
        return "RegisterPages/SchoolRegister";
    }

    @PostMapping("/escola/save")
    public String completeRegister(@ModelAttribute("escola") EscolaDTO escolaDTO) {
        Escola escola = escolaDTO.toEscola();
        schoolService.registerSchool(escola);
        return "redirect:/index";
    }

    @PostMapping("/aluno/save")
    public String studentRegister(
            @ModelAttribute("aluno") AlunoDTO alunoDTO,
            @RequestParam String escolaEmail
    ) {
        if(escolaEmail==null){
            return "redirect:/escola/management";
        }
        Aluno aluno = alunoDTO.toAluno();
        studentService.save(aluno);
        return "redirect:/escola/management";
    }
}