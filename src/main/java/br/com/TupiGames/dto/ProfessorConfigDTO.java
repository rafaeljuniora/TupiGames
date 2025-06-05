package br.com.TupiGames.dto;

public class ProfessorConfigDTO {
    private String nomeProfessor;
    private String dataNascimento;
    private String email;
    private String senha;
    private String emailAtual;

    public ProfessorConfigDTO() {
    }

    public ProfessorConfigDTO(String nomeProfessor, String dataNascimento, String email, String senha) {
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
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

    public String getEmailAtual() {
        return emailAtual;
    }

    public void setEmailAtual(String emailAtual) {
        this.emailAtual = emailAtual;
    }
}