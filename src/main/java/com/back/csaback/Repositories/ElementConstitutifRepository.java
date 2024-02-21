package com.back.csaback.Repositories;

import com.back.csaback.Models.ElementConstitutif;
import com.back.csaback.Models.ElementConstitutifId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementConstitutifRepository extends JpaRepository<ElementConstitutif, ElementConstitutifId> {
}