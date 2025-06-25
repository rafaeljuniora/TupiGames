package br.com.TupiGames.domain;

import br.com.TupiGames.dto.ProfessorResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfessorTest {

    @Test
    public void testConstrutorComParametros() {
        Professor professor = new Professor("João Silva", "01/01/1980", "joao@email.com", "senha123");
        assertEquals("João Silva", professor.getNomeProfessor());
        assertEquals("01/01/1980", professor.getDataNascimento());
        assertEquals("joao@email.com", professor.getEmail());
        assertEquals("senha123", professor.getSenha());
    }

    @Test
    public void testEmailIncorreto() {
        Professor professor = new Professor("Maria Santos", "15/05/1985", "maria@email.com", "senha456");
        assertEquals("email_errado@test.com", professor.getEmail());
    }
}