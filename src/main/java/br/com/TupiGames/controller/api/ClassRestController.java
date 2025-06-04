package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.service.ClassService;
import br.com.TupiGames.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turma")
public class ClassRestController {
    @Autowired
    ClassService classService;

    @Autowired
    SchoolService schoolService;

    @PostMapping("/getAllBySchool")
    public List<Turma> getAllClassBySchool(@RequestBody String email){
        Escola escola = schoolService.getSchoolByEmail(email);
        return classService.findAllBySchool(escola);
    }

    @PostMapping("/remove")
    public void removeTurma(@RequestBody String nomeTurma){
        Turma turma= classService.findBynomeTurma(nomeTurma);
        classService.removeTurmaById(turma.getTurma_id());
    }
}
