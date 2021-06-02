package com.eloquently.services;

import com.eloquently.dao.AbonneeDao;
import com.eloquently.model.Abonnee;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AbonneeService {
    @Autowired
    private AbonneeDao abonneeDao;

    //methode pour recuperer tous les abonnées
    public List<Abonnee> getAllAbonnees() {
        List<Abonnee> abonnees= new ArrayList<Abonnee>();
        abonneeDao.findAll().forEach(abonnees::add);
        return abonnees;
    }
    //methode pour ajouter un abonnée
    @Transactional
    public Abonnee addAbonnee(Abonnee abonnee){
        if(this.abonneeDao.findByEmail(abonnee.getEmail())==null) {
            abonneeDao.save(abonnee);
            return abonnee;
        }
        else return null;
    }
    public ResponseEntity<Abonnee> updateAbonnee(Abonnee abonnee) throws ResourceNotFoundException {
        Abonnee abonnee1=this.abonneeDao.findById(abonnee.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException("aucun abonnee avec cet id"));
        abonnee1.setNom(abonnee.getNom());
        abonnee1.setPrenom(abonnee.getPrenom());
        abonnee1.setEmail(abonnee.getEmail());
        abonnee1.setPassword(abonnee.getPassword());
        abonnee1.setNiveau(abonnee.getNiveau());
        Abonnee abonneeUpdate=this.abonneeDao.save(abonnee1);
        return ResponseEntity.ok(abonneeUpdate);
    }
    public Abonnee getAbonneeByEmail(String emailAbonnee){
        return this.abonneeDao.findByEmail(emailAbonnee);
    }

    public void deleteAbonneeByEmail(String emailAbonnee){
        Abonnee abonnee=abonneeDao.findByEmail(emailAbonnee);
        abonneeDao.delete(abonnee);
    }
}
