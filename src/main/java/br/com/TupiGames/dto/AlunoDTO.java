package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Aluno;

public class AlunoDTO {
    private Long aluno_id;
    private String nomeAluno;
    private String senha;

    public AlunoDTO(String nomeAluno, String senha) {
        this.nomeAluno = nomeAluno;
        this.senha = senha;
    }

    public AlunoDTO(Long aluno_id, String nomeAluno, String senha) {
        this.aluno_id = aluno_id;
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

    public Long getAluno_id() {
        return aluno_id;
    }

    public Aluno toAlunoWithId(){return new Aluno(this.aluno_id,this.nomeAluno,this.senha);}
}
