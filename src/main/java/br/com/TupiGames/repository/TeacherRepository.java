package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Professor,Long> {
    Optional<Professor> findByEmail(String email);
    List<Professor> findAllByescola (Escola escola);
    @Query("SELECT DISTINCT t FROM Professor p JOIN p.turmas t WHERE p.id = :professorId")
    List<Turma> findTurmasByProfessorId(@Param("professorId") Long professorId);
}
