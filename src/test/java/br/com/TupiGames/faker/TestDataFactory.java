package br.com.TupiGames.faker;

import com.github.javafaker.Faker;
import br.com.TupiGames.domain.*;

import java.time.LocalDate;
import java.util.*;

public class TestDataFactory {

    private static final Faker faker = new Faker();

    // Adiciona método utilitário para setar campos privados via reflexão
    private static void setField(Object obj, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Escola buildEscola() {
        Escola escola = new Escola();
        setField(escola, "nomeEscola", faker.company().name());
        setField(escola, "email", faker.internet().emailAddress());
        setField(escola, "senha", faker.internet().password());
        System.out.println("[FAKER] Escola criada: " + escola);
        return escola;
    }

    public static Professor buildProfessor() {
        Professor professor = new Professor();
        setField(professor, "nomeProfessor", faker.name().fullName());
        setField(professor, "email", faker.internet().emailAddress());
        setField(professor, "senha", faker.internet().password());
        setField(professor, "escola", buildEscola());
        System.out.println("[FAKER] Professor criado: " + professor);
        return professor;
    }

    public static Turma buildTurma() {
        Turma turma = new Turma();
        setField(turma, "nomeTurma", faker.educator().course());
        Set<Professor> professores = new HashSet<>();
        professores.add(buildProfessor());
        setField(turma, "professores", professores);
        System.out.println("[FAKER] Turma criada: " + turma);
        return turma;
    }

    public static Aluno buildAluno() {
        Aluno aluno = new Aluno();
        setField(aluno, "nomeAluno", faker.name().fullName());
        Set<Turma> turmas = new HashSet<>();
        turmas.add(buildTurma());
        setField(aluno, "turmas", turmas);
        System.out.println("[FAKER] Aluno criado: " + aluno);
        return aluno;
    }

    public static Pergunta buildPergunta() {
        Pergunta pergunta = new Pergunta();
        setField(pergunta, "enunciado", faker.lorem().sentence());
        System.out.println("[FAKER] Pergunta criada: " + pergunta);
        return pergunta;
    }

    public static Alternativa buildAlternativa() {
        Alternativa alternativa = new Alternativa();
        setField(alternativa, "texto", faker.lorem().word());
        setField(alternativa, "correta", faker.bool().bool());
        System.out.println("[FAKER] Alternativa criada: " + alternativa);
        return alternativa;
    }

    public static Questionario buildQuestionario() {
        Questionario questionario = new Questionario();
        setField(questionario, "titulo", faker.book().title());
        setField(questionario, "perguntas", new HashSet<>(List.of(buildPergunta(), buildPergunta())));
        System.out.println("[FAKER] Questionario criado: " + questionario);
        return questionario;
    }

    public static Atividade buildAtividade() {
        Atividade atividade = new Atividade();
        setField(atividade, "nomeAtividade", faker.job().title());
        List<Turma> turmas = new ArrayList<>();
        turmas.add(buildTurma());
        setField(atividade, "turmas", turmas);
        System.out.println("[FAKER] Atividade criada: " + atividade);
        return atividade;
    }

    public static Resposta buildResposta() {
        Resposta resposta = new Resposta();
        setField(resposta, "aluno", buildAluno());
        setField(resposta, "atividade", buildAtividade());
        System.out.println("[FAKER] Resposta criada: " + resposta);
        return resposta;
    }
}
