package br.com.TupiGames.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "atividade")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atividade_id;

    private String nomeAtividade;
    private long atividadeCode;
    private Boolean global;
    private String criador;
    private Long dataCriacao;

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("pergunta-atividade")
    private List<Pergunta> perguntas;

    @ManyToMany
    @JoinTable(
            name = "atividade_turma",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turmas = new ArrayList<>();

    @OneToMany(mappedBy = "atividade")
    private Set<Resposta> respostas = new HashSet<>();

    public Atividade(String nomeAtividade, Long atividadeCode, List<Pergunta> perguntas, Boolean global, String criador) {
        this.nomeAtividade = nomeAtividade;
        this.atividadeCode = atividadeCode;
        this.perguntas = perguntas;
        this.global = global;
        this.criador = criador;
    }

    public Atividade() {
    }

    public Long getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Long dataCriacao) {
        this.dataCriacao = dataCriacao;
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

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Long getAtividade_id() {
        return atividade_id;
    }
}