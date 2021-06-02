package com.eloquently.dao;

import com.eloquently.model.Createur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreateurDao extends JpaRepository<Createur, Long> {
    Createur findByEmail(String email);
}
