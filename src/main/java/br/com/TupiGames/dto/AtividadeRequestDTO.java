package br.com.TupiGames.dto;

public class AtividadeRequestDTO {
    private Long id;
    private String nomeAtividade;
    private long atividadeCode;
    private String professor;
    private Integer quantidadeQuestoes;
    private String dataAtribuicao;

    public AtividadeRequestDTO() {
    }

    public AtividadeRequestDTO(Long id, String nomeAtividade,long atividadeCode, String professor,
                               Integer quantidadeQuestoes, String dataAtribuicao) {
        this.id = id;
        this.nomeAtividade = nomeAtividade;
        this.atividadeCode = atividadeCode;
        this.professor = professor;
        this.quantidadeQuestoes = quantidadeQuestoes;
        this.dataAtribuicao = dataAtribuicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Integer getQuantidadeQuestoes() {
        return quantidadeQuestoes;
    }

    public void setQuantidadeQuestoes(Integer quantidadeQuestoes) {
        this.quantidadeQuestoes = quantidadeQuestoes;
    }

    public String getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(String dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }

    public long getAtividadeCode() {
        return atividadeCode;
    }

    public void setAtividadeCode(long atividadeCode) {
        this.atividadeCode = atividadeCode;
    }
}