package br.com.TupiGames.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "serial")
    private Long professor_id;

    private String nomeProfessor;
    // Todo - Modificar para DATE ou Epoch
    private String dataNascimento;
    private String email;
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", nullable = true)
    private Turma turma;

    public Professor(String nomeProfessor, String dataNascimento, String email, String senha) {
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public Professor() {
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Escola getEscola() {
        return escola;
    }

    public Long getId() {
        return professor_id;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Turma getTurma(){
        return turma;
    }
}
