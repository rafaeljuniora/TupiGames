package br.com.TupiGames.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespostaRankingDTO {
    private Long resposta_id;
    private Long pontos;
    private Integer acertos;
    private Integer total;
    private Long enviado;
    private String nomeAluno;

    // Construtores
    public RespostaRankingDTO() {
    }

    public RespostaRankingDTO(Long resposta_id, Long pontos, Integer acertos, Integer total, Long enviado, String nomeAluno) {
        this.resposta_id = resposta_id;
        this.pontos = pontos;
        this.acertos = acertos;
        this.total = total;
        this.enviado = enviado;
        this.nomeAluno = nomeAluno;
    }

    public Long getResposta_id() {
        return resposta_id;
    }

    public void setResposta_id(Long resposta_id) {
        this.resposta_id = resposta_id;
    }

    public Long getPontos() {
        return pontos;
    }

    public void setPontos(Long pontos) {
        this.pontos = pontos;
    }

    public Integer getAcertos() {
        return acertos;
    }

    public void setAcertos(Integer acertos) {
        this.acertos = acertos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getEnviado() {
        return enviado;
    }

    public void setEnviado(Long enviado) {
        this.enviado = enviado;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }
}