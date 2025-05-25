package br.com.TupiGames.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "atividade")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atividade_id;

    private String nomeAtividade;
    private Double atividadeCode;
    private List<Pergunta> perguntas;
    private List<Turma> turmas;

    public Atividade() {
    }

    public Atividade(String nomeAtividade, Double atividadeCode, List<Pergunta> perguntas) {
        this.nomeAtividade = nomeAtividade;
        this.atividadeCode = atividadeCode;
        this.perguntas = perguntas;
    }
}