package br.com.TupiGames.service;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.repository.ActvityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivityService {
    @Autowired
    ActvityRepository actvityRepository;
    public Atividade atividadeSave(Atividade atividade){
        return actvityRepository.save(atividade);
    }
}
