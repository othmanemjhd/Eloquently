package com.eloquently.dao;

import com.eloquently.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireDao extends JpaRepository<Commentaire, Long> {
    List<Commentaire> findByCreateur(String email);
    List<Commentaire> findByAbonnee(String email);
    List<Commentaire> findBySession(Long idCours);
}
