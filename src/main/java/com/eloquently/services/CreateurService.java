package com.eloquently.services;

import com.eloquently.dao.CreateurDao;
import com.eloquently.model.Createur;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateurService {
    @Autowired
    private CreateurDao createurDao;

    //methode pour recuperer tous les createurs
    public List<Createur> getAllCreateurs() {
        List<Createur> createurs= new ArrayList<Createur>();
        createurDao.findAll().forEach(createurs::add);
        return createurs;
    }

    //methode pour ajouter un createur
    @Transactional
    public Createur addCreateur(Createur createur){
        if(this.createurDao.findByEmail(createur.getEmail())!=null) {
            return null;
        }
        else{
            createurDao.save(createur);
            return createur;
        }

    }

    public ResponseEntity<Createur> updateCreateur(Long createurId, Createur createur) throws ResourceNotFoundException {
        Createur createur1=this.createurDao.findById(createurId)
                .orElseThrow(() -> new ResourceNotFoundException("aucun createur avec cet id"));
        createur1.setNom(createur1.getNom());
        createur1.setPrenom(createur1.getPrenom());
        createur1.setEmail(createur1.getEmail());
        createur1.setPassword(createur1.getPassword());
        createur1.setGrade(createur.getGrade());
        createur1.setSessions(createur1.getSessions());
        Createur createurUpdate=this.createurDao.save(createur1);
        return ResponseEntity.ok(createurUpdate);
    }

    public Createur getCreateurByEmail(String emailCreateur){
        return this.createurDao.findByEmail(emailCreateur);
    }
    public void deleteCreateurByEmail(String emailCreateur){
        Createur createur=createurDao.findByEmail(emailCreateur);
        createurDao.delete(createur);
    }
}
