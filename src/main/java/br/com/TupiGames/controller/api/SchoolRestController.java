package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.dto.EscolaConfigDTO;
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

    @PostMapping("/remove")
    public void removeSchool(@RequestBody Long escola_id) {
        schoolService.removeEscolaById(escola_id);
    }

    @PutMapping("/update")
    public ResponseEntity<Escola> atualizarEscola(@RequestBody EscolaDTO escolaAtualizado) {
        Escola escola = schoolService.findById(escolaAtualizado.getEscola_id());

        escola.setNomeEscola(escolaAtualizado.getNomeEscola());
        escola.setEmail(escolaAtualizado.getEmail());
        escola.setSenha(escolaAtualizado.getSenha());

        Escola escolaAtualizadoSalvo = schoolService.save(escola);
        return ResponseEntity.ok(escolaAtualizadoSalvo);
    }

    @GetMapping("/getEscolaByEmail")
    public ResponseEntity<EscolaConfigDTO> getEscolaByEmail(@RequestParam String email) {
        try {
            Escola escola = schoolService.getSchoolByEmail(email);
            if (escola != null) {
                EscolaConfigDTO escolaDTO = new EscolaConfigDTO();
                escolaDTO.setNomeEscola(escola.getNomeEscola());
                escolaDTO.setSenha(escola.getSenha());
                escolaDTO.setEmail(escola.getEmail());
                return ResponseEntity.ok(escolaDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/configuracoes")
    public ResponseEntity<?> updateSchoolConfiguration(@RequestBody EscolaConfigDTO escolaConfigDTO) {

        try {
            Escola escola = schoolService.getSchoolByEmail(escolaConfigDTO.getEmail());

            if (escola != null) {
                if (escolaConfigDTO.getSenha() != null && !escolaConfigDTO.getSenha().trim().isEmpty()) {
                    escola.setSenha(escolaConfigDTO.getSenha());
                }


                if (escolaConfigDTO.getNovoEmail() != null && !escolaConfigDTO.getNovoEmail().trim().isEmpty()) {
                    escola.setEmail(escolaConfigDTO.getNovoEmail());
                }

                if (escolaConfigDTO.getNomeEscola() != null && !escolaConfigDTO.getNomeEscola().trim().isEmpty()) {
                    escola.setNomeEscola(escolaConfigDTO.getNomeEscola());
                }

                schoolService.save(escola);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}