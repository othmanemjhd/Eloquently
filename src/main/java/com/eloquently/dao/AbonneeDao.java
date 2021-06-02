package com.eloquently.dao;

import com.eloquently.model.Abonnee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbonneeDao extends JpaRepository<Abonnee, Long> {
    Abonnee findByEmail(String email);
}