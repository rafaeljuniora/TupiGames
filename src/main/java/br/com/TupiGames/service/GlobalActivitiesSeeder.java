package br.com.TupiGames.service;

import br.com.TupiGames.domain.*;
import br.com.TupiGames.repository.ActvityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GlobalActivitiesSeeder implements CommandLineRunner {
    @Autowired
    private ActvityRepository actvityRepository;

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 50; i++) {
            String nomeAtividade = "Atividade Global " + i;
            long atividadeCode = 1000 + i;
            List<Pergunta> perguntas = new ArrayList<>();
            for (int j = 1; j <= 3; j++) {
                String enunciado = "Pergunta " + j + " da Atividade " + i;
                Questionario questionario = new Questionario(false, true, false, enunciado);
                List<Alternativa> alternativas = new ArrayList<>();
                for (int k = 1; k <= 4; k++) {
                    String valor = "Alternativa " + k;
                    boolean acerto = (k == 1);
                    alternativas.add(new Alternativa(false, true, valor, acerto, null));
                }
                Pergunta pergunta = new Pergunta(questionario, alternativas, null);

                for (Alternativa alt : alternativas) {
                    alt.setPergunta(pergunta);
                }
                perguntas.add(pergunta);
            }
            Atividade atividade = new Atividade(nomeAtividade, atividadeCode, perguntas, true, "escola");
            for (Pergunta pergunta : perguntas) {
                pergunta.setAtividade(atividade);
            }
            actvityRepository.save(atividade);
        }
    }
}

