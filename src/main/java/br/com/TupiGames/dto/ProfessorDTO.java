package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;

public class ProfessorDTO {
    private String primeiroNome;
    private String sobreNome;
    // Todo - Modificar para DATE ou Epoch
    private String dataNascimento;
    private String email;
    private String senha;

    public ProfessorDTO(String primeiroNome, String sobreNome, String dataNascimento, String email, String senha) {
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public ProfessorDTO() {
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
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
        return new Professor(this.primeiroNome, this.sobreNome, this.dataNascimento, this.email, this.senha);
    }
}
