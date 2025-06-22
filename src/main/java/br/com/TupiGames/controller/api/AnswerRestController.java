package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Resposta;
import br.com.TupiGames.dto.AlunoRespostaRequestDTO;
import br.com.TupiGames.dto.RespostaDTO;
import br.com.TupiGames.dto.RespostaRankingDTO;
import br.com.TupiGames.repository.AnswerRepository;
import br.com.TupiGames.service.ActivityService;
import br.com.TupiGames.service.AnswerService;
import br.com.TupiGames.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @Autowired
    AnswerRepository answerRepository;

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
    public RespostaRankingDTO getUserAnswerPoints(@RequestBody AlunoRespostaRequestDTO alunoRespostaRequestDTO) {
        Aluno aluno = studentService.getStudentByName(alunoRespostaRequestDTO.getNomeAluno());
        Atividade atividade = activityService.findByatividadeCode(alunoRespostaRequestDTO.getAtividadeCode());
        Resposta resposta = answerService.findByAlunoIdAndAtividadeId(aluno.getId(),atividade.getAtividade_id());

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

    @GetMapping("/getByAluno")
    public ResponseEntity<List<Resposta>> getRespostasByAluno(@RequestParam String nomeAluno) {
        try {

            System.out.println("\n========= INÍCIO DIAGNÓSTICO DE RESPOSTAS DO ALUNO =========");
            System.out.println("Buscando respostas para o aluno: " + nomeAluno);


            Aluno aluno = null;
            try {
                aluno = studentService.getStudentByName(nomeAluno);
            } catch (Exception e) {
                System.out.println("Erro ao buscar aluno pelo nome: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.notFound().build();
            }

            if (aluno == null) {
                System.out.println("Aluno não encontrado: " + nomeAluno);
                return ResponseEntity.notFound().build();
            }

            System.out.println("Aluno encontrado com ID: " + aluno.getId() + ", Nome: " + aluno.getNomeAluno());


            try {
                System.out.println("Verificando respostas do aluno diretamente via repository...");
                List<Resposta> respostasDiretas = answerRepository.findByAlunoIdOrderByEnviadoDesc(aluno.getId());
                System.out.println("Respostas encontradas diretamente via repository: " +
                                   (respostasDiretas != null ? respostasDiretas.size() : 0));

                if (respostasDiretas != null) {
                    System.out.println("Detalhes das respostas (via repository):");
                    for (Resposta r : respostasDiretas) {
                        System.out.println(" - ID: " + r.getResposta_id() +
                                          ", Atividade: " + (r.getAtividade() != null ? r.getAtividade().getNomeAtividade() : "N/A") +
                                          ", Pontos: " + r.getPontos() +
                                          ", Data: " + r.getEnviado());
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar respostas diretamente: " + e.getMessage());
                e.printStackTrace();
            }


            List<Resposta> respostas = answerService.findAllByAlunoId(aluno.getId());

            System.out.println("Respostas encontradas via service: " + (respostas != null ? respostas.size() : 0));

            if (respostas != null) {
                System.out.println("Detalhes das respostas (via service):");
                for (Resposta r : respostas) {
                    System.out.println(" - ID: " + r.getResposta_id() +
                                      ", Atividade: " + (r.getAtividade() != null ? r.getAtividade().getNomeAtividade() : "N/A") +
                                      ", Pontos: " + r.getPontos() +
                                      ", Data: " + r.getEnviado());
                }
            }

            System.out.println("Enviando " + (respostas != null ? respostas.size() : 0) + " respostas para o frontend");
            System.out.println("========= FIM DIAGNÓSTICO DE RESPOSTAS DO ALUNO =========\n");

            return ResponseEntity.ok(respostas);
        } catch (Exception e) {
            System.out.println("Erro ao buscar respostas: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Collections.emptyList());
        }
    }

    @GetMapping("/getLast3ByAluno")
    public ResponseEntity<List<Resposta>> getLast3RespostasByAluno(@RequestParam String nomeAluno) {
        try {
            System.out.println("\n========= BUSCANDO 3 ÚLTIMAS RESPOSTAS DO ALUNO =========");
            System.out.println("Buscando últimas 3 respostas para o aluno: " + nomeAluno);


            Aluno aluno = studentService.getStudentByName(nomeAluno);

            if (aluno == null) {
                System.out.println("Aluno não encontrado: " + nomeAluno);
                return ResponseEntity.notFound().build();
            }

            System.out.println("Aluno encontrado com ID: " + aluno.getId() + ", Nome: " + aluno.getNomeAluno());


            List<Resposta> todasRespostas = answerRepository.findByAlunoIdOrderByEnviadoDesc(aluno.getId());


            List<Resposta> ultimasRespostas = todasRespostas.stream()
                    .sorted((r1, r2) -> Long.compare(r2.getResposta_id(), r1.getResposta_id())) // ordenar por ID decrescente
                    .limit(3)
                    .collect(Collectors.toList());

            System.out.println("Total de respostas do aluno no banco: " +
                (todasRespostas != null ? todasRespostas.size() : 0));
            System.out.println("Últimas respostas selecionadas: " +
                (ultimasRespostas != null ? ultimasRespostas.size() : 0));

            if (ultimasRespostas != null && !ultimasRespostas.isEmpty()) {
                System.out.println("Detalhes das últimas respostas:");
                for (Resposta r : ultimasRespostas) {
                    System.out.println(" - ID: " + r.getResposta_id() +
                                      ", Atividade: " + (r.getAtividade() != null ? r.getAtividade().getNomeAtividade() : "N/A") +
                                      ", Pontos: " + r.getPontos() +
                                      ", Data: " + r.getEnviado());
                }
            }

            System.out.println("Enviando " + (ultimasRespostas != null ? ultimasRespostas.size() : 0) + " últimas respostas para o frontend");
            System.out.println("========= FIM BUSCA 3 ÚLTIMAS RESPOSTAS DO ALUNO =========\n");

            return ResponseEntity.ok(ultimasRespostas);
        } catch (Exception e) {
            System.out.println("Erro ao buscar últimas respostas: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Collections.emptyList());
        }
=======
    @PostMapping("getAllAnswersByActivity")
    public List<RespostaDTO> getAllAnswersByActivity(@RequestBody Long atividadeCode){
        Atividade atividade = activityService.findByatividadeCode(atividadeCode);
        List<Resposta> respostas = answerService.findAllByAtividade(atividade);
        return respostas.stream()
                .map(RespostaDTO::new)
                .collect(Collectors.toList());
    }
}
