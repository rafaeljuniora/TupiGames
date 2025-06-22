package br.com.TupiGames.service;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Resposta;
import br.com.TupiGames.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public Resposta respostaSave(Resposta resposta){
        return answerRepository.save(resposta);
    }

    public List<Resposta> getTop10AnswersByActivity(Long atividade_id){
        return answerRepository.findTop10ByAtividadeIdOrderByPontosDescEnviadoAsc(atividade_id);
    }

    public Resposta findByAlunoIdAndAtividadeId(Long aluno_id, Long atividade_id){
        return answerRepository.findByAlunoAndAtividade(aluno_id,atividade_id);
    }

    public List<Resposta> findAllByAlunoId(Long alunoId) {
        // Primeiro tenta com o método original
        List<Resposta> respostas = answerRepository.findByAlunoIdOrderByEnviadoDesc(alunoId);

        // Se não retornar nada, tenta com o método alternativo
        if (respostas == null || respostas.isEmpty()) {
            System.out.println("Método original não retornou respostas, tentando método alternativo...");
            respostas = answerRepository.findAllByAlunoId(alunoId);
        }

        System.out.println("Total de respostas encontradas no service: " + (respostas != null ? respostas.size() : 0));

        // Limitar para apenas as 3 últimas respostas (já ordenadas por data de envio decrescente)
        if (respostas != null && respostas.size() > 3) {
            respostas = respostas.stream().limit(3).collect(Collectors.toList());
            System.out.println("Limitando para as 3 respostas mais recentes");
        }

        return respostas;
=======
    public List<Resposta> findAllByAtividade(Atividade atividade){
        return answerRepository.findAllByAtividade(atividade);

    }
}
