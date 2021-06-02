package com.eloquently.dao;


import com.eloquently.model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RessourceDao extends JpaRepository<Ressource, Long> {
    Optional<Ressource> findByNomDocument(String nomDocument);
    void deleteByIdRessource(Long id);
}
