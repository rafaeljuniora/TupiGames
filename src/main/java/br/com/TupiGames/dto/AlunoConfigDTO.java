package br.com.TupiGames.dto;

public class AlunoConfigDTO {
    private String nomeAluno;
    private String senha;

    public AlunoConfigDTO() {
    }

    public AlunoConfigDTO(String nomeAluno, String senha) {
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
}