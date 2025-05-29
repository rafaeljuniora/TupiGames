package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Turma;

import java.util.List;

public class TurmaDTO {
    private String nomeTurma;
    private String periodo;
    private Integer qntAlunos;
    private List<Long> selectedProfessores;
    private List<Long> selectedAlunos;

    public TurmaDTO(String nomeTurma, String periodo, Integer qntAlunos, List<Long> selectedProfessores, List<Long> selectedAlunos) {
        this.nomeTurma = nomeTurma;
        this.periodo = periodo;
        this.qntAlunos = qntAlunos;
        this.selectedProfessores = selectedProfessores;
        this.selectedAlunos = selectedAlunos;
    }

    public TurmaDTO() {
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getQntAlunos() {
        return qntAlunos;
    }

    public void setQntAlunos(Integer qntAlunos) {
        this.qntAlunos = qntAlunos;
    }

    public Turma toTurma() {
        return new Turma(this.nomeTurma,this.periodo,this.qntAlunos);
    }

    public List<Long> getSelectedProfessores() {
        return selectedProfessores;
    }

    public void setSelectedProfessores(List<Long> selectedProfessores) {
        this.selectedProfessores = selectedProfessores;
    }

    public List<Long> getSelectedAlunos() {
        return selectedAlunos;
    }

    public void setSelectedAlunos(List<Long> selectedAlunos) {
        this.selectedAlunos = selectedAlunos;
    }
}
