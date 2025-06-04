package br.com.TupiGames.service;

import br.com.TupiGames.domain.Resposta;
import br.com.TupiGames.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Resposta findByAlunoId(Long aluno_id){
        return answerRepository.findByaluno_id(aluno_id);
    }
}
