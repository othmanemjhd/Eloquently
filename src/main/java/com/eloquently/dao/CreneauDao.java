package com.eloquently.dao;

import com.eloquently.model.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreneauDao extends JpaRepository<Creneau, Long> {
    void deleteByIdCreneau(Long idCreneau);
}
