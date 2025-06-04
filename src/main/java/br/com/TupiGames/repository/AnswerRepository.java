package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository <Resposta, Long> {
    @Query("SELECT r FROM Resposta r WHERE r.atividade.atividade_id = ?1 ORDER BY r.pontos DESC, r.enviado ASC")
    List<Resposta> findTop10ByAtividadeIdOrderByPontosDescEnviadoAsc(Long atividadeId);

    Resposta findByaluno_id(Long aluno_id);
}
