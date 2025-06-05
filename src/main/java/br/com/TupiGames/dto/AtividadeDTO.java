package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Pergunta;
import java.util.List;

public class AtividadeDTO {
    private String nomeAtividade;
    private long atividadeCode;
    private Boolean global;
    private List<Pergunta> perguntas;
    private String nomeTurma;
    private String criador;

    public AtividadeDTO(String nomeAtividade, List<Pergunta> perguntas, Boolean global, String nomeTurma, String criador) {
        this.nomeAtividade = nomeAtividade;
        this.perguntas = perguntas;
        this.global = global;
        this.nomeTurma = nomeTurma;
        this.criador = criador;
    }

    public AtividadeDTO() {
    }

    public Atividade toAtividade(){
        return new Atividade(this.nomeAtividade,this.atividadeCode,this.perguntas,this.global,this.criador);
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public long getAtividadeCode() {
        return atividadeCode;
    }

    public void setAtividadeCode(long atividadeCode) {
        this.atividadeCode = atividadeCode;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }
}
