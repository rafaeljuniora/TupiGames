package br.com.TupiGames;

import br.com.TupiGames.domain.*;
import br.com.TupiGames.faker.TestDataFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;

class FakerTest {

    @Test
    void deveCriarAlunoComTurmaEProfessor() throws Exception {
        Aluno aluno = TestDataFactory.buildAluno();
        assertNotNull(getField(aluno, "nomeAluno"));
        Object turmas = getField(aluno, "turmas");
        assertNotNull(turmas);
        Object turma = ((java.util.Set<?>) turmas).stream().findFirst().orElse(null);
        assertNotNull(turma);
        Object professores = getField(turma, "professores");
        assertNotNull(professores);
        Object professor = ((java.util.Set<?>) professores).stream().findFirst().orElse(null);
        assertNotNull(professor);
        assertNotNull(getField(professor, "escola"));
    }

    @Test
    void deveCriarProfessorComEscola() throws Exception {
        Professor professor = TestDataFactory.buildProfessor();
        assertNotNull(getField(professor, "nomeProfessor"));
        assertNotNull(getField(professor, "escola"));
    }

    @Test
    void deveCriarAtividadeComProfessorETurma() throws Exception {
        Atividade atividade = TestDataFactory.buildAtividade();
        assertNotNull(getField(atividade, "nomeAtividade"));
        Object turmas = getField(atividade, "turmas");
        assertNotNull(turmas);
        Object turma = ((java.util.List<?>) turmas).stream().findFirst().orElse(null);
        assertNotNull(turma);
    }

    @Test
    void deveCriarRespostaComAlunoPerguntaEAlternativa() throws Exception {
        Resposta resposta = TestDataFactory.buildResposta();
        assertNotNull(getField(resposta, "aluno"));
        assertNotNull(getField(resposta, "atividade"));
    }

    // Método utilitário para acessar campos privados via reflexão
    private Object getField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
