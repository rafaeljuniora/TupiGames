package br.com.TupiGames.domain;

import br.com.TupiGames.dto.AlunoResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Entity
@Table(name = "aluno")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "serial")
    private Long aluno_id;

    private String nomeAluno;
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    @ManyToMany(mappedBy = "alunos", cascade = CascadeType.REMOVE)
    private Set<Turma> turmas = new HashSet<>();

    @OneToMany(mappedBy = "aluno")
    private Set<Resposta> respostas = new HashSet<>();

    public Aluno() {
    }

    public Aluno(String nomeAluno, String senha) {
        this.nomeAluno = nomeAluno;
        this.senha = senha;
    }

    public Aluno(Long aluno_id, String nomeAluno, String senha) {
        this.aluno_id = aluno_id;
        this.nomeAluno = nomeAluno;
        this.senha = senha;
    }

    public Long getAluno_id() {
        return aluno_id;
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

    public Long getId() {
        return aluno_id;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public void removerTurma(Turma turma) {
        this.turmas.remove(turma);
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    public AlunoResponseDTO toDTO() {
        List<String> turmasNomes = this.turmas.stream()
                .map(Turma::getNomeTurma)
                .collect(Collectors.toList());

        return new AlunoResponseDTO(
                this.aluno_id,
                this.nomeAluno,
                this.senha,
                turmasNomes
        );
    }
}