package br.com.TupiGames.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AtividadeTest {

    @Test
    public void testConstrutorComParametros() {
        List<Pergunta> perguntas = new ArrayList<>();
        Atividade atividade = new Atividade("Matemática Básica", 12345L, perguntas, true, "Professor João");

        assertEquals("Matemática Básica", atividade.getNomeAtividade());
        assertEquals(12345L, atividade.getAtividadeCode());
        assertEquals(perguntas, atividade.getPerguntas());
        assertEquals(true, atividade.getGlobal());
        assertEquals("Professor João", atividade.getCriador());
    }

    @Test
    public void testCriadorIncorreto() {
        List<Pergunta> perguntas = new ArrayList<>();
        Atividade atividade = new Atividade("História do Brasil", 67890L, perguntas, false, "Professor Maria");

        assertEquals("Professor Pedro", atividade.getCriador());
    }
}