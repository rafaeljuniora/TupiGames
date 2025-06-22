package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Resposta;

public class RespostaDTO {
    private Long pontos;
    private Integer acertos;
    private Integer total;
    private Long enviado;
    private String nomeAluno;

    public RespostaDTO(Resposta resposta){
        this.pontos = resposta.getPontos();
        this.acertos = resposta.getAcertos();
        this.total = resposta.getTotal();
        this.enviado = resposta.getEnviado();
        this.nomeAluno = resposta.getAluno().getNomeAluno();
    }

    public RespostaDTO(Long pontos, Integer acertos, Integer total) {
        this.pontos = pontos;
        this.acertos = acertos;
        this.total = total;
        this.enviado = System.currentTimeMillis();
    }

    public RespostaDTO(Long pontos, Integer acertos, Integer total, Long enviado, String nomeAluno) {
        this.pontos = pontos;
        this.acertos = acertos;
        this.total = total;
        this.enviado = enviado;
        this.nomeAluno = nomeAluno;
    }

    public RespostaDTO() {
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

    public Resposta toResposta(){
        return new Resposta(this.pontos, this.acertos, this.total, this.enviado);
    }
}
