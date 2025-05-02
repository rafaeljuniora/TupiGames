package br.com.TupiGames.domain;

import java.util.List;

public class Escola {
    private String nomeEscola;
    private String email;
    private String senha;

    private List<Turma> turmas;
    private List<Professor> professores;
    private List<Aluno> alunos;

    public Escola(String nomeEscola, String email, String senha) {
        this.nomeEscola = nomeEscola;
        this.email = email;
        this.senha = senha;
    }
}
