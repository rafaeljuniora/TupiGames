package br.com.TupiGames.service;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Aluno save(Aluno aluno) {
        return studentRepository.save(aluno);
    }

    public Aluno getStudentByName(String primeiroNome,String sobreNome) {
        return studentRepository.findByPrimeiroNomeAndSobreNome(primeiroNome,sobreNome)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}
