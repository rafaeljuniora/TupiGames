package br.com.TupiGames.service;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public Professor save(Professor professor) {
        return teacherRepository.save(professor);
    }

    public Professor getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    public List<Professor> getAllBySchool(Escola escola){
        return teacherRepository.findAllByescola(escola);
    }

    public Professor findById(Long professor_id){
        return teacherRepository.findById(professor_id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    public List<Turma> getTurmasByProfessorId(Long professorId) {
        return teacherRepository.findTurmasByProfessorId(professorId);
    }

    public void removeProfessorById(Long professor_id){
        try {
            teacherRepository.deleteById(professor_id);
            System.out.println("Professor com ID " + professor_id + " removido com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao remover professor com ID " + professor_id + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
