package br.com.TupiGames.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "serial")
    private Long aluno_id;

    private String nomeAluno;
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    @ManyToMany(mappedBy = "alunos")
    private Set<Turma> turmas = new HashSet<>();


    public Aluno() {
    }

    public Aluno(String nomeAluno, String senha) {
        this.nomeAluno = nomeAluno;
        this.senha = senha;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return aluno_id;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public void removerTurma(Turma turma) {
        this.turmas.remove(turma);
    }
}