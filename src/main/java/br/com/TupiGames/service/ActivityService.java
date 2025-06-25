package br.com.TupiGames.service;

import br.com.TupiGames.domain.Alternativa;
import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Pergunta;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.repository.ActvityRepository;
import br.com.TupiGames.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    ActvityRepository actvityRepository;

    @Autowired
    ClassRepository classRepository;

    @Transactional
    public Atividade salvar(Atividade atividade) {
        atividade = actvityRepository.save(atividade);

        if(atividade.getPerguntas() != null) {
            for(Pergunta pergunta : atividade.getPerguntas()) {
                pergunta.setAtividade(atividade);

                if(pergunta.getAlternativas() != null) {
                    for(Alternativa alternativa : pergunta.getAlternativas()) {
                        alternativa.setPergunta(pergunta);
                    }
                }
            }
        }

        return actvityRepository.save(atividade);
    }

    public Atividade findByatividadeCode(long atividadeCode){
        return actvityRepository.findByatividadeCode(atividadeCode);
    }

    public List<Atividade> findByTurma(Turma turma){
        return actvityRepository.findByturmas(turma);
    }

    public List<Atividade> findAll(){
        return actvityRepository.findAll();
    }

}