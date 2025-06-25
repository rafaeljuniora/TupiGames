package br.com.TupiGames.domain;

import br.com.TupiGames.domain.*;
import br.com.TupiGames.dto.AtividadeDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
@SpringBootTest
public class AtividadeTest {

    private AtividadeDTO atividadeDto;
    private final String nomeAtividade = "Prova de Matemática";
    private final long atividadeCode = 123456;
    private final Boolean global = true;
    private final String nomeTurma = "Turma A";
    private final String criador = "professor@escola.com";

    private Pergunta pergunta1;
    private Pergunta pergunta2;

    @Before
    public void setUp() {
        Alternativa alternativa1 = new Alternativa();
        alternativa1.setValor("Alternativa A");
        alternativa1.setAcerto(true);

        Alternativa alternativa2 = new Alternativa();
        alternativa2.setValor("Alternativa B");
        alternativa2.setAcerto(false);

        Questionario questionario1 = new Questionario();
        questionario1.setValor("Quanto é 2+2?");
        questionario1.setEnunciado(true);

        Questionario questionario2 = new Questionario();
        questionario2.setValor("Qual a raiz quadrada de 16?");
        questionario2.setEnunciado(true);

        pergunta1 = new Pergunta();
        pergunta1.setQuestionario(questionario1);
        pergunta1.setAlternativas(Arrays.asList(alternativa1, alternativa2));

        pergunta2 = new Pergunta();
        pergunta2.setQuestionario(questionario2);
        pergunta2.setAlternativas(Arrays.asList(alternativa1, alternativa2));

        atividadeDto = new AtividadeDTO();
        atividadeDto.setNomeAtividade(nomeAtividade);
        atividadeDto.setAtividadeCode(atividadeCode);
        atividadeDto.setGlobal(global);
        atividadeDto.setPerguntas(Arrays.asList(pergunta1, pergunta2));
        atividadeDto.setNomeTurma(nomeTurma);
        atividadeDto.setCriador(criador);
    }

    @Test
    public void testConstrutorCompleto() {
        AtividadeDTO dtoCompleto = new AtividadeDTO(
                nomeAtividade,
                Arrays.asList(pergunta1, pergunta2),
                global,
                nomeTurma,
                criador
        );

        assertEquals(nomeAtividade, dtoCompleto.getNomeAtividade());
        assertEquals(2, dtoCompleto.getPerguntas().size());
        assertEquals(global, dtoCompleto.getGlobal());
        assertEquals(nomeTurma, dtoCompleto.getNomeTurma());
        assertEquals(criador, dtoCompleto.getCriador());
    }

    @Test
    public void testConstrutorVazio() {
        AtividadeDTO vazio = new AtividadeDTO();

        assertNull(vazio.getNomeAtividade());
        assertNull(vazio.getPerguntas());
        assertNull(vazio.getGlobal());
        assertNull(vazio.getNomeTurma());
        assertNull(vazio.getCriador());
        assertEquals(0, vazio.getAtividadeCode());
    }

    @Test
    public void testToAtividade() {
        Atividade atividade = atividadeDto.toAtividade();

        assertEquals(nomeAtividade, atividade.getNomeAtividade());
        assertEquals(atividadeCode, atividade.getAtividadeCode().longValue());
        assertEquals(global, atividade.getGlobal());
        assertEquals(criador, atividade.getCriador());
        assertEquals(2, atividade.getPerguntas().size());
        assertNull(atividade.getAtividade_id());
    }

    @Test
    public void testSettersEGetters() {
        atividadeDto.setNomeAtividade("Novo Nome");
        atividadeDto.setAtividadeCode(654321);
        atividadeDto.setGlobal(false);
        atividadeDto.setPerguntas(Arrays.asList(pergunta1));
        atividadeDto.setNomeTurma("Turma B");
        atividadeDto.setCriador("outro@professor.com");

        assertEquals("Novo Nome", atividadeDto.getNomeAtividade());
        assertEquals(654321, atividadeDto.getAtividadeCode());
        assertEquals(false, atividadeDto.getGlobal());
        assertEquals(1, atividadeDto.getPerguntas().size());
        assertEquals("Turma B", atividadeDto.getNomeTurma());
        assertEquals("outro@professor.com", atividadeDto.getCriador());
    }

    @Test
    public void testToAtividadeComPerguntasNulas() {
        atividadeDto.setPerguntas(null);
        Atividade atividade = atividadeDto.toAtividade();

        assertNull(atividade.getPerguntas());
    }

    @Test
    public void testToAtividadeComValoresNulos() {
        AtividadeDTO dtoNulo = new AtividadeDTO();
        Atividade atividade = dtoNulo.toAtividade();

        assertNull(atividade.getNomeAtividade());
        assertEquals(0, atividade.getAtividadeCode().longValue());
        assertNull(atividade.getGlobal());
        assertNull(atividade.getPerguntas());
        assertNull(atividade.getCriador());
    }
}