package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;

import java.util.Set;
import java.util.stream.Collectors;

public class TurmaRequestDTO {
    private Long turma_id;
    private String nomeTurma;
    private String periodo;
    private Integer qntAlunos;
    private Integer quantidadeAlunosAtual;
    private Set<String> professores;

    public TurmaRequestDTO(Turma turma, boolean isProfessorView) {
        this.turma_id = turma.getTurma_id();
        this.nomeTurma = turma.getNomeTurma();
        this.periodo = turma.getPeriodo();
        this.qntAlunos = turma.getQntAlunos();
        this.quantidadeAlunosAtual = turma.getAlunos() != null ? turma.getAlunos().size() : 0;
        if (!isProfessorView) {
            this.professores = turma.getProfessores() != null ?
                    turma.getProfessores().stream()
                            .map(Professor::getNomeProfessor)
                            .collect(Collectors.toSet()) :
                    Set.of();
        } else {
            this.professores = null;
        }
    }

    public Long getTurma_id() {
        return turma_id;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Integer getQntAlunos() {
        return qntAlunos;
    }

    public Integer getQuantidadeAlunosAtual() {
        return quantidadeAlunosAtual;
    }

    public Set<String> getProfessores() {
        return professores;
    }

    public void setTurma_id(Long turma_id) {
        this.turma_id = turma_id;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setQntAlunos(Integer qntAlunos) {
        this.qntAlunos = qntAlunos;
    }

    public void setQuantidadeAlunosAtual(Integer quantidadeAlunosAtual) {
        this.quantidadeAlunosAtual = quantidadeAlunosAtual;
    }

    public void setProfessores(Set<String> professores) {
        this.professores = professores;
    }
}