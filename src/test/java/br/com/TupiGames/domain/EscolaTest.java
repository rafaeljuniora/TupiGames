package br.com.TupiGames.domain;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.dto.EscolaDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EscolaTest {

    private EscolaDTO escolaDTO;

    @Before
    public void setUp() {
        escolaDTO = new EscolaDTO("Escola Teste", "teste@escola.com", "senha123");
    }

    @Test
    public void testConstrutorComParametros() {
        assertEquals("Escola Teste", escolaDTO.getNomeEscola());
        assertEquals("teste@escola.com", escolaDTO.getEmail());
        assertEquals("senha123", escolaDTO.getSenha());
    }

    @Test
    public void testConstrutorVazio() {
        EscolaDTO vazio = new EscolaDTO();
        assertNull(vazio.getNomeEscola());
        assertNull(vazio.getEmail());
        assertNull(vazio.getSenha());
    }

    @Test
    public void testSettersEGetters() {
        escolaDTO.setNomeEscola("Nova Escola");
        escolaDTO.setEmail("novo@email.com");
        escolaDTO.setSenha("novaSenha");

        assertEquals("Nova Escola", escolaDTO.getNomeEscola());
        assertEquals("novo@email.com", escolaDTO.getEmail());
        assertEquals("novaSenha", escolaDTO.getSenha());
    }

    @Test
    public void testToEscola() {
        Escola escola = escolaDTO.toEscola();

        assertEquals("Escola Teste", escola.getNomeEscola());
        assertEquals("teste@escola.com", escola.getEmail());
        assertEquals("senha123", escola.getSenha());
        assertNull(escola.getEscola_id());
    }

    @Test
    public void testToEscolaComCamposNulos() {
        EscolaDTO dtoNulo = new EscolaDTO();
        Escola escola = dtoNulo.toEscola();

        assertNull(escola.getNomeEscola());
        assertNull(escola.getEmail());
        assertNull(escola.getSenha());
    }
}