package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.*;
import br.com.TupiGames.dto.AtividadeDTO;
import br.com.TupiGames.dto.AtividadeRequestDTO;
import br.com.TupiGames.dto.RespostaDTO;
import br.com.TupiGames.service.ActivityService;
import br.com.TupiGames.service.ClassService;
import br.com.TupiGames.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/atividade")
public class ActivityRestController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClassService classService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/save")
    public ResponseEntity<Atividade> createActivity(@RequestBody AtividadeDTO atividadeDTO) {
        atividadeDTO.setAtividadeCode((long) (Math.random() * 99999999));
        Turma turma = classService.findBynomeTurma(atividadeDTO.getNomeTurma());
        Atividade atividade = atividadeDTO.toAtividade();
        Long data = System.currentTimeMillis();

        try {
            Professor professor = teacherService.getTeacherByEmail(atividadeDTO.getCriador());
            if(professor != null) {
                professor.setUltimaVezAtivo(data);
                teacherService.save(professor);
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar professor: " + e.getMessage());
        }

        atividade.adicionarTurma(turma);
        atividade.setDataCriacao(data);
        Atividade atividadeSalva = activityService.salvar(atividade);
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeSalva);
    }

    @PostMapping("/getByCode")
    public ResponseEntity<Atividade> getByCode(@RequestBody Long atividadeCode) {
        Atividade atividade = activityService.findByatividadeCode(atividadeCode);
        if (atividade == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(atividade);
        }
    }

    @PostMapping("/getActivityByCode")
    public Atividade getActivityByCode(@RequestBody Long atividadeCode) {
        return activityService.findByatividadeCode(atividadeCode);
    }

    @PostMapping("/getActivityByTurma")
    public List<AtividadeRequestDTO> getActivityByTurma(@RequestBody Long id){
        Turma turma = classService.findById(id);
        List<Atividade> atividades = activityService.findByTurma(turma);

        return atividades.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/getAllActivities")
    public List<AtividadeRequestDTO> getAllActivities() {
        List<Atividade> atividades = activityService.findAll();

        return atividades.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AtividadeRequestDTO convertToDto(Atividade atividade) {
        AtividadeRequestDTO dto = new AtividadeRequestDTO();
        dto.setId(atividade.getAtividade_id());
        dto.setNomeAtividade(atividade.getNomeAtividade());
        dto.setAtividadeCode(atividade.getAtividadeCode());
        dto.setProfessor(atividade.getCriador());
        dto.setQuantidadeQuestoes(atividade.getPerguntas() != null ? atividade.getPerguntas().size() : 0);

        if (atividade.getDataCriacao() != null) {
            Date date = new Date(atividade.getDataCriacao());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dto.setDataAtribuicao(sdf.format(date));
        } else {
            dto.setDataAtribuicao("Data não disponível");
        }

        return dto;
    }
}