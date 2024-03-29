package com.back.csaback.Repositories;

import com.back.csaback.Models.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Authentification,Long> {
    //Optional<Authentification> findByLoginConnection(String loginConnection);

    Boolean existsByLoginConnection(String username);

    Boolean existsByPseudoConnection(String email);

    @Query("SELECT a FROM Authentification a WHERE a.loginConnection = :loginOrPseudo OR a.pseudoConnection = :loginOrPseudo")
    Optional<Authentification> findByLoginOrPseudo(@Param("loginOrPseudo") String loginOrPseudo);

    @Query("SELECT a FROM Authentification a WHERE a.id BETWEEN 1 AND 10")
    List<Authentification> findOriginals();
}
