package br.com.TupiGames.controller;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/aluno")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/home")
    public String homeStudent(){
        return "HomePages/StudentHome";
    }

    @GetMapping("/buscar")
    @ResponseBody
    public List<Aluno> buscarAlunos(@RequestParam(required = false) String nomeAluno,
                                    @RequestParam(required = false) String nomeTurma) {
        if ((nomeAluno == null || nomeAluno.isEmpty()) && (nomeTurma == null || nomeTurma.isEmpty())) {
            return studentRepository.findAll();
        }
        return studentRepository.findByNomeAlunoContainingIgnoreCaseOrTurmas_NomeTurmaContainingIgnoreCase(
                nomeAluno != null ? nomeAluno : "",
                nomeTurma != null ? nomeTurma : ""
        );
    }
}