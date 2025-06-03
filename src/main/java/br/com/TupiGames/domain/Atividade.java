package br.com.TupiGames.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atividade")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atividade_id;

    private String nomeAtividade;
    private long atividadeCode;
    private Boolean global;

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pergunta> perguntas;

    @ManyToMany
    @JoinTable(
            name = "atividade_turma",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turmas = new ArrayList<>();

    public Atividade(String nomeAtividade, Long atividadeCode, List<Pergunta> perguntas, Boolean global) {
        this.nomeAtividade = nomeAtividade;
        this.atividadeCode = atividadeCode;
        this.perguntas = perguntas;
        this.global = global;
    }

    public Atividade() {
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public Long getAtividadeCode() {
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

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }
}