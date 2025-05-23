package br.com.TupiGames.service;

import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    ClassRepository classRepository;

    public Turma turmaregister(Turma turma){
        return classRepository.save(turma);
    }
}
