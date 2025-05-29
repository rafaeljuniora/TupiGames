package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findBynomeAluno (String nomeAluno);
    List<Aluno> findAllByescola (Escola escola);
}
