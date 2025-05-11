package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Aluno, Long> {

}
