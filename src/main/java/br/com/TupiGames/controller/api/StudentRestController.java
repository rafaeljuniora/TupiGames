package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.dto.AlunoConfigDTO;
import br.com.TupiGames.dto.AlunoDTO;
import br.com.TupiGames.dto.AlunoResponseDTO;
import br.com.TupiGames.service.SchoolService;
import br.com.TupiGames.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/getAllBySchool")
    public List<AlunoResponseDTO> getAllStudentsBySchool(@RequestBody String email){
        Escola escola = schoolService.getSchoolByEmail(email);
        List<Aluno> alunos = studentService.getAllBySchool(escola);
        return alunos.stream()
                .map(Aluno::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/getAlunoByNome")
    public ResponseEntity<AlunoConfigDTO> getAlunoByNome(@RequestBody String nomeAluno) {
        try {
            Aluno aluno = studentService.getStudentByName(nomeAluno);
            if (aluno != null) {
                AlunoConfigDTO alunoDTO = new AlunoConfigDTO();
                alunoDTO.setNomeAluno(aluno.getNomeAluno());
                alunoDTO.setSenha(aluno.getSenha());
                return ResponseEntity.ok(alunoDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/configuracoes")
    public ResponseEntity<?> updateStudentConfiguration(@RequestBody AlunoConfigDTO alunoConfigDTO) {
        try {
            Aluno aluno = studentService.getStudentByName(alunoConfigDTO.getNomeAluno());
            if (aluno != null) {
                if (alunoConfigDTO.getSenha() != null && !alunoConfigDTO.getSenha().trim().isEmpty()) {
                    aluno.setSenha(alunoConfigDTO.getSenha());
                }
                studentService.save(aluno);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/remove")
    public void removeStudent(@RequestBody Long aluno_id) {
        studentService.removeAlunoById(aluno_id);
    }

    @PutMapping("/update")
    public ResponseEntity<Aluno> atualizarAluno(@RequestBody AlunoDTO alunoAtualizado) {
        Aluno aluno = studentService.findById(alunoAtualizado.getAluno_id());

        aluno.setNomeAluno(alunoAtualizado.getNomeAluno());
        aluno.setSenha(alunoAtualizado.getSenha());

        Aluno alunoAtualizadoSalvo = studentService.save(aluno);
        return ResponseEntity.ok(alunoAtualizadoSalvo);
    }
}