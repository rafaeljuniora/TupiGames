package br.com.TupiGames.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlunoTest {

    private Aluno aluno;

    @Before
    public void setUp() {
        aluno = new Aluno(1L, "Carlos Souza", "minhaSenha");
    }

    @Test
    public void testConstrutorComId() {
        assertEquals(1L, aluno.getAluno_id().longValue());
        assertEquals("Carlos Souza", aluno.getNomeAluno());
        assertEquals("minhaSenha", aluno.getSenha());
    }

    @Test
    public void testConstrutorSemId() {
        Aluno alunoSemId = new Aluno("Ana Santos", "outraSenha");

        assertNull(alunoSemId.getAluno_id());
        assertEquals("Ana Santos", alunoSemId.getNomeAluno());
        assertEquals("outraSenha", alunoSemId.getSenha());
    }

    @Test
    public void testSetters() {
        aluno.setNomeAluno("Novo Nome");
        aluno.setSenha("senhaAtualizada");

        assertEquals("Novo Nome", aluno.getNomeAluno());
        assertEquals("senhaAtualizada", aluno.getSenha());
    }
}