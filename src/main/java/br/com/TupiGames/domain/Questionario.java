package br.com.TupiGames.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Questionario {
    private Boolean imagem;
    private Boolean enunciado;
    @Column(name = "imagem_enunciado")
    private Boolean imagemEnunciado;
    private String valor;


    public Questionario(Boolean imagem, Boolean enunciado, Boolean imagemEnunciado, String valor) {
        this.imagem = imagem;
        this.enunciado = enunciado;
        this.imagemEnunciado = imagemEnunciado;
        this.valor = valor;
    }

    public Questionario() {
    }

    public Boolean getImagem() {
        return imagem;
    }

    public void setImagem(Boolean imagem) {
        this.imagem = imagem;
    }

    public Boolean getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(Boolean enunciado) {
        this.enunciado = enunciado;
    }

    public Boolean getImagemEnunciado() {
        return imagemEnunciado;
    }

    public void setImagemEnunciado(Boolean imagemEnunciado) {
        this.imagemEnunciado = imagemEnunciado;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}