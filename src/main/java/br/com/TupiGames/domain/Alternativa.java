package br.com.TupiGames.domain;

public class Alternativa {
    private Boolean imagem;
    private Boolean enunciado;
    private String valor;
    private Boolean acerto;

    public Alternativa(Boolean imagem, Boolean enunciado, String valor, Boolean acerto) {
        this.imagem = imagem;
        this.enunciado = enunciado;
        this.valor = valor;
        this.acerto = acerto;
    }
}
