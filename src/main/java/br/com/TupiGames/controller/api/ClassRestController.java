package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.dto.TurmaRequestDTO;
import br.com.TupiGames.service.ClassService;
import br.com.TupiGames.service.SchoolService;
import br.com.TupiGames.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/turma")
public class ClassRestController {
    @Autowired
    ClassService classService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    TeacherService teacherService;

    @PostMapping("/getAllBySchool")
    public List<TurmaRequestDTO> getAllClassBySchool(@RequestBody String email) {
        Escola escola = schoolService.getSchoolByEmail(email);
        List<Turma> turmas = classService.findAllBySchool(escola);
        return turmas.stream()
                .map(turma -> new TurmaRequestDTO(turma, false))
                .collect(Collectors.toList());
    }

    @PostMapping("getAllByTeacher")
    public List<TurmaRequestDTO> getAllClassByTeacher(@RequestBody String email) {
        Professor professor = teacherService.getTeacherByEmail(email);
        List<Turma> turmas = classService.findAllByTeacher(professor);
        return turmas.stream()
                .map(turma -> new TurmaRequestDTO(turma, true))
                .collect(Collectors.toList());
    }

    @PostMapping("/remove")
    public void removeTurma(@RequestBody String nomeTurma) {
        Turma turma = classService.findBynomeTurma(nomeTurma);
        classService.removeTurmaById(turma.getTurma_id());
    }
}
