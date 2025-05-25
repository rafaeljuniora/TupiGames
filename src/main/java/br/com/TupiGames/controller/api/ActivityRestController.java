package br.com.TupiGames.controller.api;

import br.com.TupiGames.domain.Atividade;
import br.com.TupiGames.dto.AtividadeDTO;
import br.com.TupiGames.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/atividade")
public class ActivityRestController {
    @Autowired
    ActivityService activityService;

    @PostMapping("/register")
    public ResponseEntity<Atividade> createActivity(@RequestParam AtividadeDTO atividadeDTO){
        Atividade atividade = atividadeDTO.toAtividade();
        Atividade atividadeSaved = activityService.atividadeSave(atividade);
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeSaved);
    }
}
