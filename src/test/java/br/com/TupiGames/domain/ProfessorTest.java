package br.com.TupiGames.domain;


import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.dto.ProfessorDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfessorTest {

    private ProfessorDTO professorDto;
    private final Long testId = 1L;
    private final String testNome = "Maria Souza";
    private final String testDataNasc = "10/03/1985";
    private final String testEmail = "maria@escola.com";
    private final String testSenha = "senha456";
    private final long testTimestamp = System.currentTimeMillis();

    @Before
    public void setUp() {
        professorDto = new ProfessorDTO(testId, testNome, testDataNasc, testEmail, testSenha);
    }

    @Test
    public void testConstrutorComId() {
        assertEquals(testId, professorDto.getProfessor_id());
        assertEquals(testNome, professorDto.getNomeProfessor());
        assertEquals(testDataNasc, professorDto.getDataNascimento());
        assertEquals(testEmail, professorDto.getEmail());
        assertEquals(testSenha, professorDto.getSenha());
    }

    @Test
    public void testConstrutorSemId() {
        ProfessorDTO dtoSemId = new ProfessorDTO(testNome, testDataNasc, testEmail, testSenha);

        assertNull(dtoSemId.getProfessor_id());
        assertEquals(testNome, dtoSemId.getNomeProfessor());
        assertEquals(testDataNasc, dtoSemId.getDataNascimento());
        assertEquals(testEmail, dtoSemId.getEmail());
        assertEquals(testSenha, dtoSemId.getSenha());
    }

    @Test
    public void testConstrutorVazio() {
        ProfessorDTO vazio = new ProfessorDTO();

        assertNull(vazio.getProfessor_id());
        assertNull(vazio.getNomeProfessor());
        assertNull(vazio.getDataNascimento());
        assertNull(vazio.getEmail());
        assertNull(vazio.getSenha());
    }

    @Test
    public void testConstrutorDeProfessor() {
        Professor professor = new Professor(testId, testNome, testDataNasc, testEmail, testSenha, testTimestamp);
        ProfessorDTO dtoFromProfessor = new ProfessorDTO(professor);

        assertEquals(testNome, dtoFromProfessor.getNomeProfessor());
        assertEquals(testDataNasc, dtoFromProfessor.getDataNascimento());
        assertEquals(testEmail, dtoFromProfessor.getEmail());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").format(new Date(testTimestamp)),
                dtoFromProfessor.getUltimaVezAtivo());
    }

    @Test
    public void testToProfessor() {
        Professor professor = professorDto.toProfessor();

        assertEquals(testNome, professor.getNomeProfessor());
        assertEquals(testDataNasc, professor.getDataNascimento());
        assertEquals(testEmail, professor.getEmail());
        assertEquals(testSenha, professor.getSenha());
        assertNull(professor.getProfessor_id());
    }

    @Test
    public void testToProfessorWithId() {
        Professor professor = professorDto.toProfessorWithId();

        assertEquals(testId, professor.getProfessor_id());
        assertEquals(testNome, professor.getNomeProfessor());
        assertEquals(testDataNasc, professor.getDataNascimento());
        assertEquals(testEmail, professor.getEmail());
        assertEquals(testSenha, professor.getSenha());
    }

    @Test
    public void testSetters() {
        professorDto.setNomeProfessor("Novo Nome");
        professorDto.setDataNascimento("01/01/2000");
        professorDto.setEmail("novo@email.com");
        professorDto.setSenha("novasenha");
        professorDto.setUltimaVezAtivo("01/01/2023");

        assertEquals("Novo Nome", professorDto.getNomeProfessor());
        assertEquals("01/01/2000", professorDto.getDataNascimento());
        assertEquals("novo@email.com", professorDto.getEmail());
        assertEquals("novasenha", professorDto.getSenha());
        assertEquals("01/01/2023", professorDto.getUltimaVezAtivo());
    }

    @Test
    public void testFormatarDataNula() {
        Professor professor = new Professor(testId, testNome, testDataNasc, testEmail, testSenha, null);
        ProfessorDTO dto = new ProfessorDTO(professor);

        assertEquals("N/A", dto.getUltimaVezAtivo());
    }
}