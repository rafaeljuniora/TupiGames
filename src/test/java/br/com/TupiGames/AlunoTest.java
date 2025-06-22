package br.com.TupiGames.domain;

import br.com.TupiGames.dto.AlunoResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest {

    @Test
    public void testConstrutorVazio() {
        Aluno aluno = new Aluno();
        assertNotNull(aluno);
    }

    @Test
    public void testConstrutorComNomeESenha() {
        Aluno aluno = new Aluno("João", "senha123");
        assertEquals("João", aluno.getNomeAluno());
        assertEquals("senha123", aluno.getSenha());
    }

    @Test
    public void testConstrutorCompleto() {
        Aluno aluno = new Aluno(1L, "Maria", "senha456");
        assertEquals(1L, aluno.getAluno_id());
        assertEquals("Maria", aluno.getNomeAluno());
        assertEquals("senha456", aluno.getSenha());
    }

    @Test
    public void testSetNomeAluno() {
        Aluno aluno = new Aluno();
        aluno.setNomeAluno("Pedro");
        assertEquals("Pedro", aluno.getNomeAluno());
    }

    @Test
    public void testSetSenha() {
        Aluno aluno = new Aluno();
        aluno.setSenha("novaSenha");
        assertEquals("novaSenha", aluno.getSenha());
    }

    @Test
    public void testGetId() {
        Aluno aluno = new Aluno(5L, "Ana", "senha");
        assertEquals(5L, aluno.getId());
    }

    @Test
    public void testAdicionarTurma() {
        Aluno aluno = new Aluno();
        Turma turma = new Turma();
        aluno.adicionarTurma(turma);
        assertTrue(aluno.getTurmas().contains(turma));
    }

    @Test
    public void testRemoverTurma() {
        Aluno aluno = new Aluno();
        Turma turma = new Turma();
        aluno.adicionarTurma(turma);
        aluno.removerTurma(turma);
        assertFalse(aluno.getTurmas().contains(turma));
    }

    @Test
    public void testToDTO() {
        Aluno aluno = new Aluno(1L, "Carlos", "senha123");
        AlunoResponseDTO dto = aluno.toDTO();
        assertEquals(1L, dto.getAluno_id());
        assertEquals("Carlos", dto.getNomeAluno());
        assertEquals("senha123", dto.getSenha());
    }
}