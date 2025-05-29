package br.com.TupiGames.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long aluno_id;

    private String primeiroNome;
    private String sobreNome;
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    public Aluno() {
    }

    public Aluno(String primeiroNome, String sobreNome, String senha) {
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
        this.senha = senha;
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

    public Long getId() {
        return aluno_id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Escola getEscola() {
        return escola;
    }
}
