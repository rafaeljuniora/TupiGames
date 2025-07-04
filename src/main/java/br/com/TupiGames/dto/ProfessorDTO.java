package br.com.TupiGames.dto;

import br.com.TupiGames.domain.Professor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfessorDTO {
    private Long professor_id;
    private String nomeProfessor;
    // Todo - Modificar para DATE ou Epoch
    private String dataNascimento;
    private String email;
    private String senha;
    private String ultimaVezAtivo;

    public ProfessorDTO(Professor professor){
        this.nomeProfessor = professor.getNomeProfessor();
        this.dataNascimento = professor.getDataNascimento();
        this.email = professor.getEmail();
        this.ultimaVezAtivo = formatarData(professor.getUltimaVezAtivo());
    }

    public ProfessorDTO(Long professor_id, String nomeProfessor, String dataNascimento, String email, String senha, String ultimaVezAtivo) {
        this.professor_id = professor_id;
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.ultimaVezAtivo = ultimaVezAtivo;
    }

    private String formatarData(Long timestamp) {
        if (timestamp == null) return "N/A";
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(timestamp));
    }

    public ProfessorDTO(String nomeProfessor, String dataNascimento, String email, String senha) {
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public ProfessorDTO(Long professor_id, String nomeProfessor, String dataNascimento, String email, String senha) {
        this.professor_id = professor_id;
        this.nomeProfessor = nomeProfessor;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public ProfessorDTO() {
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getProfessor_id() {
        return professor_id;
    }

    public String getUltimaVezAtivo() {
        return ultimaVezAtivo;
    }

    public void setUltimaVezAtivo(String  ultimaVezAtivo) {
        this.ultimaVezAtivo = ultimaVezAtivo;
    }

    public Professor toProfessor() {
        return new Professor(this.nomeProfessor, this.dataNascimento, this.email, this.senha);
    }

    public Professor toProfessorWithId() {
        return new Professor(this.professor_id,this.nomeProfessor, this.dataNascimento, this.email, this.senha);
    }
}
