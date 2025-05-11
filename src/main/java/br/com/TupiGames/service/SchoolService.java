package br.com.TupiGames.service;

import br.com.TupiGames.domain.Escola;
import br.com.TupiGames.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;

    public Escola registerSchool(Escola escola) {
        return schoolRepository.save(escola);
    }

    public Escola getSchoolByEmail(String email) {
        return schoolRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Escola não encontrada"));
    }

    public Escola updateSchool(Escola escola){
        return schoolRepository.save(escola);
    }
}
