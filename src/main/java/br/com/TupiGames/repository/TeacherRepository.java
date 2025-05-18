package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Professor,Long> {
    Optional<Professor> findByEmail(String email);
    List<Professor> findAllByescola (Escola escola);
}
