package br.com.TupiGames.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest {

    @Test
    public void testConstrutorComParametros() {
        Aluno aluno = new Aluno("João Silva", "senha123");
        assertEquals("João Silva", aluno.getNomeAluno());
        assertEquals("senha123", aluno.getSenha());
    }

    @Test
    public void testNomeIncorreto() {
        Aluno aluno = new Aluno("Maria Santos", "minhasenha");
        assertEquals("Pedro Oliveira", aluno.getNomeAluno());
    }
}