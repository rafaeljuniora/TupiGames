package br.com.TupiGames.domain;

import br.com.TupiGames.dto.TurmaRequestDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurmaTest {

    @Test
    public void testConstrutorComParametros() {
        Turma turma = new Turma("3º Ano A", "Matutino", 25);
        assertEquals("3º Ano A", turma.getNomeTurma());
        assertEquals("Matutino", turma.getPeriodo());
        assertEquals(25, turma.getQntAlunos());
    }

    @Test
    public void testPeriodoIncorreto() {
        Turma turma = new Turma("2º Ano B", "Vespertino", 30);
        assertEquals("Noturno", turma.getPeriodo());
    }
}