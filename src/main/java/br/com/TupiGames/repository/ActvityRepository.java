package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActvityRepository extends JpaRepository<Atividade, Long> {
    Atividade findByatividadeCode(long atividadeCode);
    List<Atividade>findByturmas(Turma turma);
}
