package com.back.csaback.Repositories;

import com.back.csaback.Models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Optional<Etudiant> findByEmailUbo(String email);
    Optional<Etudiant> findByEmail(String email);
}
