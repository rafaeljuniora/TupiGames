package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnswerRepository extends JpaRepository <Resposta, Long> {
    @Query("SELECT r FROM Resposta r WHERE r.atividade.atividade_id = ?1 ORDER BY r.pontos DESC, r.enviado ASC")
    List<Resposta> findTop10ByAtividadeIdOrderByPontosDescEnviadoAsc(Long atividadeId);

    @Query("SELECT r FROM Resposta r WHERE r.aluno.aluno_id = ?1 AND r.atividade.atividade_id = ?2")
    Resposta findByAlunoAndAtividade(Long aluno_id, Long atividade_id);

    @Query("SELECT r FROM Resposta r JOIN r.aluno a WHERE a.id = ?1 ORDER BY r.enviado DESC")
    List<Resposta> findByAlunoIdOrderByEnviadoDesc(Long alunoId);

    @Query(value = "SELECT r FROM Resposta r JOIN r.aluno a WHERE a.id = ?1 ORDER BY r.resposta_id DESC")
    List<Resposta> findTop3ByAlunoIdOrderByIdDesc(Long alunoId, Pageable pageable);

    @Query("SELECT r FROM Resposta r WHERE r.aluno.id = ?1")
    List<Resposta> findAllByAlunoId(Long alunoId);
=======
    List<Resposta> findAllByAtividade(Atividade atividade);

}
