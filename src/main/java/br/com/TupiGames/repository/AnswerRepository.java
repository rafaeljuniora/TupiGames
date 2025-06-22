package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository <Resposta, Long> {
    @Query("SELECT r FROM Resposta r WHERE r.atividade.atividade_id = ?1 ORDER BY r.pontos DESC, r.enviado ASC")
    List<Resposta> findTop10ByAtividadeIdOrderByPontosDescEnviadoAsc(Long atividadeId);

    @Query("SELECT r FROM Resposta r WHERE r.aluno.aluno_id = ?1 AND r.atividade.atividade_id = ?2")
    Resposta findByAlunoAndAtividade(Long aluno_id, Long atividade_id);

    List<Resposta> findAllByAtividade(Atividade atividade);
}
