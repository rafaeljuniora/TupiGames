package br.com.TupiGames.service;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.repository.ActvityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    ActvityRepository actvityRepository;
    public Atividade salvar(Atividade atividade) {
        // Configurar relações bidirecionais
        if(atividade.getPerguntas() != null) {
            atividade.getPerguntas().forEach(pergunta -> {
                pergunta.setAtividade(atividade);
                if(pergunta.getAlternativas() != null) {
                    pergunta.getAlternativas().forEach(alt -> alt.setPergunta(pergunta));
                }
            });
        }

        return actvityRepository.save(atividade);
    }
}
