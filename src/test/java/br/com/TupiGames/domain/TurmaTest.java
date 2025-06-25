package br.com.TupiGames.domain;

import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.dto.TurmaDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class TurmaTest {

    private TurmaDTO turmaDto;
    private final String nomeTurma = "Turma A";
    private final String periodo = "Matutino";
    private final Integer qntAlunos = 30;
    private final List<Long> professoresIds = Arrays.asList(1L, 2L);
    private final List<Long> alunosIds = Arrays.asList(3L, 4L, 5L);

    @Before
    public void setUp() {
        turmaDto = new TurmaDTO(nomeTurma, periodo, qntAlunos, professoresIds, alunosIds);
    }

    @Test
    public void testConstrutorCompleto() {
        assertEquals(nomeTurma, turmaDto.getNomeTurma());
        assertEquals(periodo, turmaDto.getPeriodo());
        assertEquals(qntAlunos, turmaDto.getQntAlunos());
        assertEquals(professoresIds, turmaDto.getSelectedProfessores());
        assertEquals(alunosIds, turmaDto.getSelectedAlunos());
    }

    @Test
    public void testConstrutorVazio() {
        TurmaDTO vazio = new TurmaDTO();

        assertNull(vazio.getNomeTurma());
        assertNull(vazio.getPeriodo());
        assertNull(vazio.getQntAlunos());
        assertNull(vazio.getSelectedProfessores());
        assertNull(vazio.getSelectedAlunos());
    }

    @Test
    public void testToTurma() {
        Turma turma = turmaDto.toTurma();

        assertEquals(nomeTurma, turma.getNomeTurma());
        assertEquals(periodo, turma.getPeriodo());
        assertEquals(qntAlunos, turma.getQntAlunos());
        assertNull(turma.getTurma_id());
    }

    @Test
    public void testSetters() {
        turmaDto.setNomeTurma("Turma B");
        turmaDto.setPeriodo("Vespertino");
        turmaDto.setQntAlunos(25);
        turmaDto.setSelectedProfessores(Arrays.asList(6L, 7L));
        turmaDto.setSelectedAlunos(Arrays.asList(8L, 9L, 10L));

        assertEquals("Turma B", turmaDto.getNomeTurma());
        assertEquals("Vespertino", turmaDto.getPeriodo());
        assertEquals(Integer.valueOf(25), turmaDto.getQntAlunos());
        assertEquals(Arrays.asList(6L, 7L), turmaDto.getSelectedProfessores());
        assertEquals(Arrays.asList(8L, 9L, 10L), turmaDto.getSelectedAlunos());
    }

    @Test
    public void testToTurmaComValoresNulos() {
        TurmaDTO dtoNulo = new TurmaDTO();
        Turma turma = dtoNulo.toTurma();

        assertNull(turma.getNomeTurma());
        assertNull(turma.getPeriodo());
        assertNull(turma.getQntAlunos());
    }

    @Test
    public void testListasVazias() {
        TurmaDTO dtoComListasVazias = new TurmaDTO(
                "Turma C",
                "Noturno",
                20,
                Arrays.asList(),
                Arrays.asList()
        );

        assertTrue(dtoComListasVazias.getSelectedProfessores().isEmpty());
        assertTrue(dtoComListasVazias.getSelectedAlunos().isEmpty());
    }
}