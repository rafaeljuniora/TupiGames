package br.com.TupiGames.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEscola;
    private String email;
    private String senha;

    @OneToMany
    private List<Turma> turmas;
    @OneToMany
    private List<Professor> professores;
    @OneToMany
    private List<Aluno> alunos;

    public Escola(String nomeEscola, String email, String senha) {
        this.nomeEscola = nomeEscola;
        this.email = email;
        this.senha = senha;
    }

    public Escola() {
    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
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
}
