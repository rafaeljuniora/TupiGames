package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Pergunta;
import br.com.TupiGames.domain.Turma;

import java.util.List;


public class AtividadeDTO {
    private String nomeAtividade;
    private Double atividadeCode;
    private List<Pergunta> perguntas;

    public AtividadeDTO(String nomeAtividade, List<Pergunta> perguntas) {
        this.nomeAtividade = nomeAtividade;
        this.perguntas = perguntas;
        this.atividadeCode = Math.random()*99999999;
    }

    public AtividadeDTO() {
    }

    public Atividade toAtividade(){
        return new Atividade(this.nomeAtividade,this.atividadeCode,this.perguntas);
    }
}
