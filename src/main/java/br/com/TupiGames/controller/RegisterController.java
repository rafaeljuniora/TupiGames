package br.com.TupiGames.controller;

import br.com.TupiGames.domain.Aluno;
import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.domain.Professor;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.dto.AlunoDTO;
import br.com.TupiGames.dto.EscolaDTO;
import br.com.TupiGames.dto.ProfessorDTO;
import br.com.TupiGames.dto.TurmaDTO;
import br.com.TupiGames.service.ClassService;
import br.com.TupiGames.service.SchoolService;
import br.com.TupiGames.service.StudentService;
import br.com.TupiGames.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    SchoolService schoolService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ClassService classService;

    @GetMapping("/escola")
    public String showRegistrationForm(Model model) {
        model.addAttribute("escola", new EscolaDTO());
        return "RegisterPages/SchoolRegister";
    }

    @PostMapping("/escola/save")
    public String completeRegister(@ModelAttribute("escola") EscolaDTO escolaDTO) {
        Escola escola = escolaDTO.toEscola();
        schoolService.registerSchool(escola);
        return "redirect:/";
    }

    @PostMapping("/aluno/save")
    public String studentRegister(
            @ModelAttribute("aluno") AlunoDTO alunoDTO,
            @RequestParam String modStudEscolaEmail
    ) {
        if(modStudEscolaEmail==null){
            return "redirect:/escola/management";
        }
        Escola escola = schoolService.getSchoolByEmail(modStudEscolaEmail);
        Aluno aluno = alunoDTO.toAluno();
        aluno.setEscola(escola);
        studentService.save(aluno);
        return "redirect:/escola/management";
    }

    @PostMapping("/professor/save")
    public String teacherRegister(
            @ModelAttribute("professor") ProfessorDTO professorDTO,
            @RequestParam String modTeacherEscolaEmail
    ) {
        if(modTeacherEscolaEmail==null){
            return "redirect:/escola/management";
        }
        Escola escola = schoolService.getSchoolByEmail(modTeacherEscolaEmail);
        Professor professor = professorDTO.toProfessor();
        professor.setEscola(escola);
        teacherService.save(professor);
        return "redirect:/escola/management";
    }

    @PostMapping("/turma/save")
    @Transactional
    public String classRegister(
            @ModelAttribute("turma") TurmaDTO turmaDTO,
            @RequestParam String modClassEscolaEmail
    ) {
        if(modClassEscolaEmail==null){
            return "redirect:/escola/management";
        }
        Escola escola = schoolService.getSchoolByEmail(modClassEscolaEmail);
        Turma turma = turmaDTO.toTurma();
        turma.setEscola(escola);
        classService.turmaregister(turma);

        if (turmaDTO.getSelectedProfessores() != null) {
            for (Long professorId : turmaDTO.getSelectedProfessores()) {
                Professor professor = teacherService.findById(professorId);
                professor.adicionarTurma(turma);
                turma.getProfessores().add(professor);
                teacherService.save(professor);
            }
        }

        if (turmaDTO.getSelectedAlunos() != null) {
            for (Long alunoId : turmaDTO.getSelectedAlunos()) {
                Aluno aluno = studentService.findById(alunoId);
                aluno.adicionarTurma(turma);
                turma.getAlunos().add(aluno);
                studentService.save(aluno);
            }
        }

        return "redirect:/escola/management";
    }
}