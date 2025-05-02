package br.com.TupiGames.repository;

import br.com.TupiGames.domain.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<Escola, Long> {
    Optional<Escola> findByEmail(String email);
}
