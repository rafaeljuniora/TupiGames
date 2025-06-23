package br.com.TupiGames.domain;

import br.com.TupiGames.dto.RespostaDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RespostaTest {

    private RespostaDTO respostaDto;
    private final Long pontos = 100L;
    private final Integer acertos = 8;
    private final Integer total = 10;
    private final Long enviado = System.currentTimeMillis();
    private final String nomeAluno = "João Silva";

    @Before
    public void setUp() {
        respostaDto = new RespostaDTO(pontos, acertos, total, enviado, nomeAluno);
    }

    @Test
    public void testConstrutorCompleto() {
        assertEquals(pontos, respostaDto.getPontos());
        assertEquals(acertos, respostaDto.getAcertos());
        assertEquals(total, respostaDto.getTotal());
        assertEquals(enviado, respostaDto.getEnviado());
        assertEquals(nomeAluno, respostaDto.getNomeAluno());
    }

    @Test
    public void testConstrutorSemEnviadoENome() {
        RespostaDTO dtoSimples = new RespostaDTO(pontos, acertos, total);

        assertEquals(pontos, dtoSimples.getPontos());
        assertEquals(acertos, dtoSimples.getAcertos());
        assertEquals(total, dtoSimples.getTotal());
        assertNotNull(dtoSimples.getEnviado());
        assertNull(dtoSimples.getNomeAluno());
    }

    @Test
    public void testConstrutorDeResposta() {
        Aluno aluno = new Aluno();
        aluno.setNomeAluno(nomeAluno);

        Resposta resposta = new Resposta();
        resposta.setPontos(pontos);
        resposta.setAcertos(acertos);
        resposta.setTotal(total);
        resposta.setEnviado(enviado);
        resposta.setAluno(aluno);

        RespostaDTO dtoFromResposta = new RespostaDTO(resposta);

        assertEquals(pontos, dtoFromResposta.getPontos());
        assertEquals(acertos, dtoFromResposta.getAcertos());
        assertEquals(total, dtoFromResposta.getTotal());
        assertEquals(enviado, dtoFromResposta.getEnviado());
        assertEquals(nomeAluno, dtoFromResposta.getNomeAluno());
    }

    @Test
    public void testConstrutorVazio() {
        RespostaDTO vazio = new RespostaDTO();

        assertNull(vazio.getPontos());
        assertNull(vazio.getAcertos());
        assertNull(vazio.getTotal());
        assertNull(vazio.getEnviado());
        assertNull(vazio.getNomeAluno());
    }

    @Test
    public void testToResposta() {
        Resposta resposta = respostaDto.toResposta();

        assertEquals(pontos, resposta.getPontos());
        assertEquals(acertos, resposta.getAcertos());
        assertEquals(total, resposta.getTotal());
        assertEquals(enviado, resposta.getEnviado());
        assertNull(resposta.getResposta_id());
        assertNull(resposta.getAluno());
    }

    @Test
    public void testSettersEGetters() {
        respostaDto.setPontos(200L);
        respostaDto.setAcertos(9);
        respostaDto.setTotal(12);
        respostaDto.setEnviado(123456789L);
        respostaDto.setNomeAluno("Maria Oliveira");

        assertEquals(Long.valueOf(200), respostaDto.getPontos());
        assertEquals(Integer.valueOf(9), respostaDto.getAcertos());
        assertEquals(Integer.valueOf(12), respostaDto.getTotal());
        assertEquals(Long.valueOf(123456789), respostaDto.getEnviado());
        assertEquals("Maria Oliveira", respostaDto.getNomeAluno());
    }

    @Test
    public void testToRespostaComValoresNulos() {
        RespostaDTO dtoNulo = new RespostaDTO();
        Resposta resposta = dtoNulo.toResposta();

        assertNull(resposta.getPontos());
        assertNull(resposta.getAcertos());
        assertNull(resposta.getTotal());
        assertNull(resposta.getEnviado());
    }
}