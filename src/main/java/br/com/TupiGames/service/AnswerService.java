package br.com.TupiGames.service;

import br.com.TupiGames.domain.Resposta;
import br.com.TupiGames.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public Resposta respostaSave(Resposta resposta){
        return answerRepository.save(resposta);
    }
}
