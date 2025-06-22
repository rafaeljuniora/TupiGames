package br.com.TupiGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/turmas")
public class ClassController {
    @GetMapping("/professor")
    public String turmaProfessor(){
        return "ClassViewPages/TeacherClassViewMacro";
    }

    @GetMapping("/professor/details")
    public String turmaDetalhadaProfessor(){
        return "ClassViewPages/TeacherClassView";
    }

    @GetMapping("/escola/details")
    public String turmaDetalhadaEscola(){
        return "ClassViewPages/SchoolClassView";
    }

    @GetMapping("/professor/details/activity")
    public String atividadesAtribuidasDaTurmaProfessor(){return "ClassViewPages/TeacherClassViewActivity";}

    @GetMapping("/escola/details/activity")
    public String atividadesAtribuidasDaTurmaEscola(){return "ClassViewPages/SchoolClassViewActivity";}
}
