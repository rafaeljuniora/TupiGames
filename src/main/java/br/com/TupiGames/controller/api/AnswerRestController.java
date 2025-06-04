package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Resposta;
import br.com.TupiGames.dto.RespostaDTO;
import br.com.TupiGames.dto.RespostaRankingDTO;
import br.com.TupiGames.service.ActivityService;
import br.com.TupiGames.service.AnswerService;
import br.com.TupiGames.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/getTop10AnswersByActivity")
    public List<RespostaRankingDTO> getTop10AnswersByActivity(@RequestBody Long atividadeCode) {
        Atividade atividade = activityService.findByatividadeCode(atividadeCode);
        List<Resposta> respostas = answerService.getTop10AnswersByActivity(atividade.getAtividade_id());

        return respostas.stream()
                .map(resposta -> new RespostaRankingDTO(
                        resposta.getResposta_id(),
                        resposta.getPontos(),
                        resposta.getAcertos(),
                        resposta.getTotal(),
                        resposta.getEnviado(),
                        resposta.getAluno().getNomeAluno()))
                .collect(Collectors.toList());
    }

    @PostMapping("/userAnswerPoints")
    public RespostaRankingDTO getUserAnswerPoints(@RequestBody String nomeAluno) {
        Aluno aluno = studentService.getStudentByName(nomeAluno);
        Resposta resposta = answerService.findByAlunoId(aluno.getId());

        if (resposta == null) {
            return null;
        }

        return new RespostaRankingDTO(
                resposta.getResposta_id(),
                resposta.getPontos(),
                resposta.getAcertos(),
                resposta.getTotal(),
                resposta.getEnviado(),
                aluno.getNomeAluno());
    }
}
