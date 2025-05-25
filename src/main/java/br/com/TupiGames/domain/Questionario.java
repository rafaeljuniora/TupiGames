package br.com.TupiGames.domain;

public class Questionario {
    private Boolean imagem;
    private Boolean enunciado;
    private Boolean imagem_enunciado;
    private String valor;

    public Questionario(Boolean imagem, Boolean enunciado, Boolean imagem_enunciado, String valor) {
        this.imagem = imagem;
        this.enunciado = enunciado;
        this.imagem_enunciado = imagem_enunciado;
        this.valor = valor;
    }
}
