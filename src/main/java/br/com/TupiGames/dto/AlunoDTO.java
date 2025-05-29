package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;

public class AlunoDTO {
    private String nomeAluno;
    private String senha;

    public AlunoDTO(String nomeAluno, String senha) {
        this.nomeAluno = nomeAluno;
        this.senha = senha;
    }

    public AlunoDTO() {
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

    public Aluno toAluno() {
        return new Aluno(this.nomeAluno, this.senha);
    }
}
