package br.com.TupiGames.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @GeneratedValue
    private Long turma_id;
    private String nomeTurma;
    private String periodo;
    private Integer qntAlunos;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Professor> professores = new ArrayList<>();

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno> alunos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    public Turma(String nomeTurma, String periodo, Integer qntAlunos) {
        this.nomeTurma = nomeTurma;
        this.periodo = periodo;
        this.qntAlunos = qntAlunos;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Escola getEscola() {
        return escola;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getQntAlunos() {
        return qntAlunos;
    }

    public void setQntAlunos(Integer qntAlunos) {
        this.qntAlunos = qntAlunos;
    }
}
