package com.back.csaback.Repositories;

import com.back.csaback.Models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {
    Optional<Enseignant> findByEmailUbo(String emailUbo);
}
