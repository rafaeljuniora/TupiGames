package br.com.TupiGames.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pergunta")
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Questionario questionario;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alternativa> alternativas;

    @ManyToOne
    @JoinColumn(name = "atividade_id")
    private Atividade atividade;

    public Pergunta(Questionario questionario, List<Alternativa> alternativas, Atividade atividade) {
        this.questionario = questionario;
        this.alternativas = alternativas;
        this.atividade = atividade;
    }

    public Pergunta() {
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
}