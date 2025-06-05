package br.com.TupiGames.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"aluno_id", "atividade_id"})
})
public class Resposta {
    @Id
    @GeneratedValue()
    private Long resposta_id;

    private Long pontos;
    private Integer acertos;
    private Integer total;
    private Long enviado;

    @ManyToOne
    @JoinColumn(name = "atividade_id")
    private Atividade atividade;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Resposta(Long pontos, Integer acertos, Integer total, Long enviado) {
        this.pontos = pontos;
        this.acertos = acertos;
        this.total = total;
        this.enviado = enviado;
    }

    public Resposta() {
    }

    public Long getPontos() {
        return pontos;
    }

    public void setPontos(Long pontos) {
        this.pontos = pontos;
    }

    public Long getResposta_id() {
        return resposta_id;
    }

    public Integer getAcertos() {
        return acertos;
    }

    public void setAcertos(Integer acertos) {
        this.acertos = acertos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getEnviado() {
        return enviado;
    }

    public void setEnviado(Long enviado) {
        this.enviado = enviado;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
