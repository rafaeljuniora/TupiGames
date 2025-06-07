package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.dto.ProfessorDTO;
import br.com.TupiGames.dto.TurmaDTO;
import br.com.TupiGames.service.SchoolService;
import br.com.TupiGames.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/professor")
public class TeacherRestController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolService schoolService;

    @PostMapping("/login")
    public ResponseEntity<?> studentLogin(
            @RequestParam String email,
            @RequestParam String senha) {

        try {
            Professor professor = teacherService.getTeacherByEmail(email);

            if (professor != null && professor.getSenha().equals(senha)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/getAllBySchool")
    public List<Professor> getAllTeachersBySchool(@RequestBody String email){
        Escola escola = schoolService.getSchoolByEmail(email);
        return teacherService.getAllBySchool(escola);
    }

    @PostMapping("/getTurmasByProfessor")
    public ResponseEntity<List<TurmaDTO>> getTurmasByProfessor(@RequestBody String email) {
        Professor professor = teacherService.getTeacherByEmail(email);
        if (professor == null) {
            return ResponseEntity.notFound().build();
        }

        List<Turma> turmas = teacherService.getTurmasByProfessorId(professor.getId());

        List<TurmaDTO> turmasDTO = turmas.stream()
                .map(t -> {
                    TurmaDTO dto = new TurmaDTO();
                    dto.setNomeTurma(t.getNomeTurma());
                    dto.setPeriodo(t.getPeriodo());
                    dto.setQntAlunos(t.getQntAlunos());

                    dto.setSelectedProfessores(t.getProfessores().stream()
                            .map(Professor::getId)
                            .collect(Collectors.toList()));

                    dto.setSelectedAlunos(t.getAlunos().stream()
                            .map(Aluno::getId)
                            .collect(Collectors.toList()));

                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(turmasDTO);
    }

    @PostMapping("/remove")
    public void removeTeacher(@RequestBody Long professor_id){
        teacherService.removeProfessorById(professor_id);
    }

    @PutMapping("/update")
    public ResponseEntity<Professor> atualizarProfessor(@RequestBody ProfessorDTO professorAtualizado) {
        Professor professor = teacherService.findById(professorAtualizado.getProfessor_id());

        professor.setNomeProfessor(professorAtualizado.getNomeProfessor());
        professor.setDataNascimento(professorAtualizado.getDataNascimento());
        professor.setEmail(professorAtualizado.getEmail());
        professor.setSenha(professorAtualizado.getSenha());

        Professor professorAtualizadoSalvo = teacherService.save(professor);
        return ResponseEntity.ok(professorAtualizadoSalvo);
    }
}
