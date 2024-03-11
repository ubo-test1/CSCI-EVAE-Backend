package com.back.csaback.Repositories;

import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.ElementConstitutifId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElementConstitutifRepository extends JpaRepository<ElementConstitutif, ElementConstitutifId> {
    Optional<ElementConstitutif> findById(ElementConstitutifId id);
}