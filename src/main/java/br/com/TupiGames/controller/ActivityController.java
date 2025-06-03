package br.com.TupiGames.controller;

import br.com.TupiGames.dto.AtividadeDTO;
import br.com.TupiGames.dto.ProfessorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atividades")
public class ActivityController {
    @GetMapping("/professores/criar")
    public String createActivity(@ModelAttribute("atividade") AtividadeDTO atividadeDTO){
        return "ActivityPages/TeacherCreateActivity";
    }
}
