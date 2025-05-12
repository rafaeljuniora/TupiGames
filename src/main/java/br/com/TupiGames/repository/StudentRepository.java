package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByPrimeiroNomeAndSobreNome (String primeiroNome,String sobreNome);
}
