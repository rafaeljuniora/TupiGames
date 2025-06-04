package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActvityRepository extends JpaRepository<Atividade, Long> {
    Atividade findByatividadeCode(long atividadeCode);
}
