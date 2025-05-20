package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;

import java.util.List;

public class TurmaDTO {
    private String nomeTurma;
    private String periodo;
    private Integer qntAlunos;
    private List<Aluno> alunos;
    private List<Professor> professores;

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
}
