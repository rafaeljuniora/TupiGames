package br.com.TupiGames.service;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Aluno save(Aluno aluno) {
        return studentRepository.save(aluno);
    }

    public Aluno getStudentByName(String nomeAluno) {
        return studentRepository.findBynomeAluno(nomeAluno)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<Aluno> getAllBySchool(Escola escola){
        return studentRepository.findAllByescola(escola);
    }

    public Aluno findById(Long aluno_id){
        return studentRepository.findById(aluno_id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public void removeAlunoById(Long aluno_id){
        studentRepository.deleteById(aluno_id);
    }

    public void deleteAll(){
        studentRepository.deleteAll();
    }

}
