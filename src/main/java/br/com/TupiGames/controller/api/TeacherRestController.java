package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/professor")
public class TeacherRestController {
    @Autowired
    TeacherService teacherService;

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
}
