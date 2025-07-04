package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;

import java.util.List;

public class EscolaDTO {
    private String nomeEscola;
    private String email;
    private String senha;

    private List<Turma> turmas;
    private List<Professor> professores;
    private List<Aluno> alunos;

    public EscolaDTO(String nomeEscola, String email, String senha) {
        this.nomeEscola = nomeEscola;
        this.email = email;
        this.senha = senha;
    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Escola toEscola() {
        return new Escola(this.nomeEscola, this.email, this.senha);
    }

    public EscolaDTO() {
    }
}
