package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;

public class AlunoDTO {
    private String primeiroNome;
    private String sobreNome;
    private String senha;

    public AlunoDTO(String primeiroNome, String sobreNome, String senha) {
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
        this.senha = senha;
    }

    public AlunoDTO() {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Aluno toAluno() {
        return new Aluno(this.primeiroNome, this.sobreNome, this.senha);
    }
}
