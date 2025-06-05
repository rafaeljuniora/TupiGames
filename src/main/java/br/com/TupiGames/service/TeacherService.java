package br.com.TupiGames.service;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.dto.ProfessorConfigDTO;
import br.com.TupiGames.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Professor updateConfiguration(ProfessorConfigDTO configDTO) {
        Professor professor = teacherRepository.findByEmail(configDTO.getEmailAtual())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (configDTO.getSenha() != null && !configDTO.getSenha().trim().isEmpty()) {
            professor.setSenha(configDTO.getSenha());
        }

        if (configDTO.getDataNascimento() != null && !configDTO.getDataNascimento().trim().isEmpty()) {
            professor.setDataNascimento(configDTO.getDataNascimento());
        }

        if (configDTO.getEmail() != null && !configDTO.getEmail().trim().isEmpty()) {
            if (!configDTO.getEmail().equals(professor.getEmail())) {
                Optional<Professor> existingProfessor = teacherRepository.findByEmail(configDTO.getEmail());
                if (existingProfessor.isPresent()) {
                    throw new RuntimeException("Email já está sendo usado por outro professor");
                }
                professor.setEmail(configDTO.getEmail());
            }
        }

        return teacherRepository.save(professor);
    }

    public Professor getConfigurationByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }
}