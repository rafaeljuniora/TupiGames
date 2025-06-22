package br.com.TupiGames.dto;

import java.util.List;

public class ProfessorResponseDTO {
    private Long professor_id;
    private String nomeProfessor;
    private String dataNascimento;
    private String email;
    private String senha;
    private List<String> turmas;

    public ProfessorResponseDTO() {}

    public ProfessorResponseDTO(Long professor_id, String nomeProfessor, String dataNascimento,
                                String email, String senha, List<String> turmas) {
        this.professor_id = professor_id;
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.turmas = turmas;
    }

    public Long getProfessor_id() { return professor_id; }
    public void setProfessor_id(Long professor_id) { this.professor_id = professor_id; }

    public String getNomeProfessor() { return nomeProfessor; }
    public void setNomeProfessor(String nomeProfessor) { this.nomeProfessor = nomeProfessor; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<String> getTurmas() { return turmas; }
    public void setTurmas(List<String> turmas) { this.turmas = turmas; }
}