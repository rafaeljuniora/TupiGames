package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository <Resposta, Long> {

}
