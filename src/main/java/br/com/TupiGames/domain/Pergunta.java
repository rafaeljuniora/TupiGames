package br.com.TupiGames.domain;

import java.util.List;
public class Pergunta {
    private Questionario questionario;
    private List<Alternativa> alternativas;

    public Pergunta(Questionario questionario, List<Alternativa> alternativas) {
        this.questionario = questionario;
        this.alternativas = alternativas;
    }
}
