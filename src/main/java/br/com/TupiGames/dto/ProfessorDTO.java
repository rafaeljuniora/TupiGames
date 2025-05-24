package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Professor;

public class ProfessorDTO {
    private String nomeProfessor;
    // Todo - Modificar para DATE ou Epoch
    private String dataNascimento;
    private String email;
    private String senha;

    public ProfessorDTO(String nomeProfessor, String dataNascimento, String email, String senha) {
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public ProfessorDTO() {
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

    public Professor toProfessor() {
        return new Professor(this.nomeProfessor, this.dataNascimento, this.email, this.senha);
    }
}
