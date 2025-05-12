package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Professor,Long> {
}
