package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.dto.EscolaDTO;
import br.com.TupiGames.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/escola")
public class SchoolRestController {
    @Autowired
    SchoolService schoolService;

    @PostMapping("/register")
    public ResponseEntity<Escola> registerSchool(@RequestBody EscolaDTO escolaDTO) {
        Escola escola = escolaDTO.toEscola();
        Escola saved = schoolService.registerSchool(escola);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String senha) {

        try {
            Escola escola = schoolService.getSchoolByEmail(email);

            if (escola != null && escola.getSenha().equals(senha)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}