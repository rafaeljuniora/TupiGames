package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.domain.Turma;
import br.com.TupiGames.dto.AtividadeDTO;
import br.com.TupiGames.service.ActivityService;
import br.com.TupiGames.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}