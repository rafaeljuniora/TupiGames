package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Resposta;
import br.com.TupiGames.dto.RespostaDTO;
import br.com.TupiGames.service.ActivityService;
import br.com.TupiGames.service.AnswerService;
import br.com.TupiGames.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resposta")
public class AnswerRestController {

    @Autowired
    AnswerService answerService;

    @Autowired
    StudentService studentService;

    @Autowired
    ActivityService activityService;

    @PostMapping("/save")
    public Resposta salvarResposta(@RequestBody RespostaDTO respostaDTO, @RequestParam String nomeAluno, @RequestParam Long atividadeCode){
        Resposta resposta = respostaDTO.toResposta();
        Aluno aluno = studentService.getStudentByName(nomeAluno);
        Atividade atividade = activityService.findByatividadeCode(atividadeCode);

        resposta.setAtividade(atividade);
        resposta.setAluno(aluno);
        resposta.setEnviado(System.currentTimeMillis());

        return answerService.respostaSave(resposta);
    }
}
