package br.com.TupiGames.service;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    ClassRepository classRepository;

    public Turma turmaregister(Turma turma){
        return classRepository.save(turma);
    }

    public Turma findBynomeTurma(String nomeTurma){
        return classRepository.findBynomeTurma(nomeTurma);
    }

    public List<Turma> findAllBySchool(Escola escola){
        return classRepository.findAllByEscola(escola);
    }

    public void removeTurmaById(Long turma_id){
        classRepository.deleteById(turma_id);
    }

    public List<Turma> findAllByTeacher(Professor professor){
        return classRepository.findAllByProfessores(professor);
    }

    public Turma findById(Long id){
        return classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }
}
