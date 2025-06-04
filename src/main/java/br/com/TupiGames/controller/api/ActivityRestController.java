package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Pergunta;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.dto.AtividadeDTO;
import br.com.TupiGames.service.ActivityService;
import br.com.TupiGames.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/atividade")
public class ActivityRestController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClassService classService;

    @PostMapping("/save")
    public ResponseEntity<Atividade> createActivity(@RequestBody AtividadeDTO atividadeDTO) {
        atividadeDTO.setAtividadeCode((long) (Math.random() * 99999999));
        Turma turma = classService.findBynomeTurma(atividadeDTO.getNomeTurma());
        Atividade atividade = atividadeDTO.toAtividade();
        atividade.adicionarTurma(turma);
        Atividade atividadeSalva = activityService.salvar(atividade);
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeSalva);
    }

    @PostMapping("/getByCode")
    public ResponseEntity<Atividade> getByCode(@RequestBody Long atividadeCode) {
        Atividade atividade = activityService.findByatividadeCode(atividadeCode);
        if(atividade==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(atividade);
        }
    }

    @PostMapping("/getActivityByCode")
    public Atividade getActivityByCode(@RequestBody Long atividadeCode) {
        return activityService.findByatividadeCode(atividadeCode);
    }
}