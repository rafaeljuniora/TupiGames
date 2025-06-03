package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.service.SchoolService;
import br.com.TupiGames.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/aluno")
public class StudentRestController {
    @Autowired
    StudentService studentService;

    @Autowired
    SchoolService schoolService;

    @PostMapping("/login")
    public ResponseEntity<?> studentLogin(
            @RequestParam String nomeAluno,
            @RequestParam String senha) {

        try {
            Aluno aluno = studentService.getStudentByName(nomeAluno);

            if (aluno != null && aluno.getSenha().equals(senha)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("getAllBySchool")
    public List<Aluno> getAllStudentsBySchool(@RequestBody String email){
        Escola escola = schoolService.getSchoolByEmail(email);
        return studentService.getAllBySchool(escola);
    }

    @PostMapping("/configuracoes")
    public ResponseEntity<?> updateStudentConfig(@RequestBody Map<String, String> config) {
        try {
            String senha = config.get("senha");

            if (senha == null || senha.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Senha é obrigatória"));
            }

            return ResponseEntity.ok(Map.of("message", "Configurações atualizadas com sucesso"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro interno do servidor"));
        }
    }
}