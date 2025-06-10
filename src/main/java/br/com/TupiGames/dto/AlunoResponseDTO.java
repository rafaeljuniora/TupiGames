package br.com.TupiGames.dto;

import java.util.List;

public class AlunoResponseDTO {
    private Long aluno_id;
    private String nomeAluno;
    private String senha;
    private List<String> turmas; // Nova propriedade para as turmas

    public AlunoResponseDTO() {
    }

    public AlunoResponseDTO(Long aluno_id, String nomeAluno, String senha) {
        this.aluno_id = aluno_id;
        this.nomeAluno = nomeAluno;
        this.senha = senha;
    }

    public AlunoResponseDTO(Long aluno_id, String nomeAluno, String senha, List<String> turmas) {
        this.aluno_id = aluno_id;
        this.nomeAluno = nomeAluno;
        this.senha = senha;
        this.turmas = turmas;
    }

    public Long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Long aluno_id) {
        this.aluno_id = aluno_id;
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

    public List<String> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<String> turmas) {
        this.turmas = turmas;
    }
}