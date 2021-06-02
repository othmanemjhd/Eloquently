package com.eloquently.controllers;

import com.eloquently.model.Abonnee;
import com.eloquently.model.Commentaire;
import com.eloquently.model.Createur;
import com.eloquently.services.AbonneeService;
import com.eloquently.services.CommentaireService;
import com.eloquently.services.CreateurService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentaireController {
    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private AbonneeService abonneeService;
    @Autowired
    private CreateurService createurService;


    @GetMapping("/createurs/{emailUser}/Commentaires")
    @ApiOperation(value = "Lister  les commentaires d'un abonnee",
            response = Commentaire.class)
    public List<Commentaire> getAllCommentaireAbonnee(@PathVariable String emailUser){
        return commentaireService.getAllCommentaireByAbonnee(emailUser);

    }

    @GetMapping("/abonnees/{emailUser}/Commentaires")
    @ApiOperation(value = "Lister  les commentaires d'un createur",
            response = Commentaire.class)
    public List<Commentaire> getAllCommentaireCreateur(@PathVariable String emailUser){
        return commentaireService.getAllCommentaireByCreateur(emailUser);

    }
    @GetMapping("/sessions/{idSession}/Commentaires")
    @ApiOperation(value = "Lister  les commentaires d'une session",
            response = Commentaire.class)
    public List<Commentaire> getAllCommentaireSession(@PathVariable Long idSession){
        return commentaireService.getAllCommentaireSession(idSession);
    }

    @PostMapping("/users/{emailUser}/commentaires")
    @ApiOperation(value = "ajouter un commentaire par un abonnee",
            response =Commentaire.class)
    public Commentaire addCommentaireAbonnee(@PathVariable String emailUserr, @Valid @RequestBody Commentaire commentaire){
        Abonnee abonnee= abonneeService.getAbonneeByEmail(emailUserr);
        commentaire.setAbonnee(abonnee);
        return commentaireService.addCommentaireAbonnee(commentaire);
    }
    @PostMapping("/createurs/{emailUser}/commentaires")
    @ApiOperation(value = "ajouter un commentaire par un createur",
            response =Commentaire.class)
    public Commentaire addCommentaireCreateur(@PathVariable String emailUserr, @Valid @RequestBody Commentaire commentaire){
        Createur createur= createurService.getCreateurByEmail(emailUserr);
        commentaire.setCreateur(createur);
        return commentaireService.addCommentaireAbonnee(commentaire);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = ("/users/{mailUser}/commentaire/{idCommentaire}"))
    @Transactional
    @ApiOperation(value = "Supprimer un commentaire",
            notes = "Supprimer un commentaire par un user",
            response = Commentaire.class)
    public void DeleteCommentaire(@PathVariable Long idCommentaire) {
        commentaireService.DeleteCommentaire(idCommentaire);
    }

}
