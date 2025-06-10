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
    public String teacherCreateActivity(@ModelAttribute("atividade") AtividadeDTO atividadeDTO){
        return "ActivityPages/TeacherCreateActivity";
    }

    @GetMapping("/escola/criar")
    public String schoolCreateActivity(@ModelAttribute("atividade") AtividadeDTO atividadeDTO){
        return "ActivityPages/SchoolCreateActivity";
    }

    @GetMapping("/aluno/realizar")
    public String realizationActivities(){
        return "ActivityPages/StudentActivitiesRealization";
    }

    @GetMapping("/aluno/feedback")
    public String feedbackStudentActivities(){
        return "ActivityPages/StudentFeedbackActivities";
    }

    @GetMapping("/aluno/ranking")
    public String rankingStudentActivities(){
        return "ActivityPages/StudentRankingActivities";
    }
}
