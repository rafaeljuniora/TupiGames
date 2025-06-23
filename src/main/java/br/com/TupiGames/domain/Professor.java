package br.com.TupiGames.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import br.com.TupiGames.dto.ProfessorResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "serial")
    private Long professor_id;

    private String nomeProfessor;
    // Todo - Modificar para DATE ou Epoc
    private String dataNascimento;
    private String email;
    private String senha;
    private Long ultimaVezAtivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    @ManyToMany(mappedBy = "professores", cascade = CascadeType.REMOVE)
    private Set<Turma> turmas = new HashSet<>();

    public Professor(String nomeProfessor, String dataNascimento, String email, String senha) {
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public Professor(Long professor_id, String nomeProfessor, String dataNascimento, String email, String senha) {
        this.professor_id = professor_id;
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public Professor(Long professor_id, String nomeProfessor, String dataNascimento, String email, String senha, Long ultimaVezAtivo) {
        this.professor_id = professor_id;
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.ultimaVezAtivo = ultimaVezAtivo;
    }

    public Professor() {
    }

    public Long getProfessor_id() {
        return professor_id;
    }

    public Long getUltimaVezAtivo() {
        return ultimaVezAtivo;
    }

    public void setUltimaVezAtivo(Long ultimaVezAtivo) {
        this.ultimaVezAtivo = ultimaVezAtivo;
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

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Escola getEscola() {
        return escola;
    }

    public Long getId() {
        return professor_id;
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public void removerTurma(Turma turma) {
        this.turmas.remove(turma);
    }

    public ProfessorResponseDTO toDTO() {
        List<String> turmasNomes = this.turmas.stream()
                .map(Turma::getNomeTurma)
                .collect(Collectors.toList());
        return new ProfessorResponseDTO(this.professor_id, this.nomeProfessor,
                this.dataNascimento, this.email, this.senha, turmasNomes);
    }
}