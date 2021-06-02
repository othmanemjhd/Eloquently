package com.eloquently.services;

import com.eloquently.dao.CommentaireDao;
import com.eloquently.model.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentaireService {
    @Autowired
    private CommentaireDao commentaireDao;

    // Récupérer tout les commentaires d'un abonnee
    public List<Commentaire> getAllCommentaireByAbonnee(final String email) {
        List<Commentaire> commentaires = new ArrayList<Commentaire>();
        commentaireDao.findByAbonnee(email).forEach(commentaires::add);
        return commentaires;
    }

    // Récupérer tout les commentaires d'un createur
    public List<Commentaire> getAllCommentaireByCreateur(final String email) {
        List<Commentaire> commentaires = new ArrayList<Commentaire>();
        commentaireDao.findByCreateur(email).forEach(commentaires::add);
        return commentaires;
    }

    // Récupérer tout les commentaires d'une session
    public List<Commentaire> getAllCommentaireSession(final Long idSession) {
        List<Commentaire> commentaires = new ArrayList<Commentaire>();
        commentaireDao.findBySession(idSession).forEach(commentaires::add);
        return commentaires;
    }

    // ajouter un commentaire par un abonnee
    @Transactional
    public Commentaire addCommentaireAbonnee(Commentaire commentaire){
        commentaireDao.save(commentaire);
        return commentaire;
    }

    // Supprimer un commentaire par un user(soit createur ou abonnee)
    public void DeleteCommentaire(Long idCommentaire) {
        commentaireDao.deleteById(idCommentaire);
    }
}
