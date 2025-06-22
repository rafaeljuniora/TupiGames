package br.com.TupiGames.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EscolaTest {

    @Test
    public void testConstrutorComParametros() {
        Escola escola = new Escola("Escola Estadual Indígena Araju Porã", "contato@escolaArajuPora.com.br", "senha123");

        assertEquals("Escola Estadual Indígena Araju Porã", escola.getNomeEscola());
        assertEquals("contato@escolaArajuPora.com.br", escola.getEmail());
        assertEquals("senha123", escola.getSenha());
    }

    @Test
    public void testEmailIncorreto() {
        Escola escola = new Escola("Colégio Estadual Indígena Kuaa Mbo'e", "admin@KuaaMboe.com.br", "minhasenha456");

        assertEquals("diretoria@KuaaMboe.edu.br", escola.getEmail());
    }
}