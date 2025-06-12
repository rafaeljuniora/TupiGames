package br.com.TupiGames.dto;

import java.util.List;

public class ProfessorResponseDTO {
    private Long professor_id;
    private String nomeProfessor;
    private List<String> turmas;

    public ProfessorResponseDTO() {}

    public ProfessorResponseDTO(Long professor_id, String nomeProfessor, List<String> turmas) {
        this.professor_id = professor_id;
        this.nomeProfessor = nomeProfessor;
        this.turmas = turmas;
    }

    public Long getProfessor_id() { return professor_id; }
    public void setProfessor_id(Long professor_id) { this.professor_id = professor_id; }

    public String getNomeProfessor() { return nomeProfessor; }
    public void setNomeProfessor(String nomeProfessor) { this.nomeProfessor = nomeProfessor; }

    public List<String> getTurmas() { return turmas; }
    public void setTurmas(List<String> turmas) { this.turmas = turmas; }
}