package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRepository extends JpaRepository <Turma, Long> {
    Turma findBynomeTurma(String nomeTurma);
    List<Turma> findAllByEscola(Escola escola);
}
