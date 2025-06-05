package br.com.TupiGames.dto;

public class AlunoRespostaRequestDTO {
    private String nomeAluno;
    private Long atividadeCode;

    public AlunoRespostaRequestDTO(String nomeAluno, Long atividadeCode) {
        this.nomeAluno = nomeAluno;
        this.atividadeCode = atividadeCode;
    }

    public AlunoRespostaRequestDTO() {
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Long getAtividadeCode() {
        return atividadeCode;
    }

    public void setAtividadeCode(Long atividadeCode) {
        this.atividadeCode = atividadeCode;
    }
}
