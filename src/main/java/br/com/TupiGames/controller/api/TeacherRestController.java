package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.dto.ProfessorConfigDTO;
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

    @PostMapping("getAllBySchool")
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

    @PostMapping("/configuracoes")
    public ResponseEntity<?> updateTeacherConfiguration(@RequestBody ProfessorConfigDTO configDTO) {
        try {
            Professor professor = teacherService.updateConfiguration(configDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Erro ao atualizar configurações: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno do servidor"));
        }
    }

    @GetMapping("/configuracoes")
    public ResponseEntity<ProfessorConfigDTO> getTeacherConfiguration(@RequestParam String email) {
        try {
            Professor professor = teacherService.getTeacherByEmail(email);

            ProfessorConfigDTO configDTO = new ProfessorConfigDTO();
            configDTO.setNomeProfessor(professor.getNomeProfessor());
            configDTO.setEmail(professor.getEmail());
            configDTO.setDataNascimento(professor.getDataNascimento());

            return ResponseEntity.ok(configDTO);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}