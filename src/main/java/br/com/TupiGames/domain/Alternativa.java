package br.com.TupiGames.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "alternativa")
public class Alternativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean imagem;
    private Boolean enunciado;

    @Column(columnDefinition = "TEXT")
    private String valor;

    private Boolean acerto;

    @ManyToOne
    @JoinColumn(name = "pergunta_id")
    @JsonBackReference
    private Pergunta pergunta;

    public Alternativa(Boolean imagem, Boolean enunciado, String valor, Boolean acerto, Pergunta pergunta) {
        this.imagem = imagem;
        this.enunciado = enunciado;
        this.valor = valor;
        this.acerto = acerto;
        this.pergunta = pergunta;
    }

    public Alternativa() {
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Boolean getAcerto() {
        return acerto;
    }

    public void setAcerto(Boolean acerto) {
        this.acerto = acerto;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }
}