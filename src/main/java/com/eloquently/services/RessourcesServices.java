package com.eloquently.services;


import com.eloquently.dao.RessourceDao;
import com.eloquently.model.Ressource;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RessourcesServices {
    @Autowired
    private RessourceDao ressourceDao;

    @Transactional
    public Ressource addRessource(Ressource ressource){
        if(ressourceDao.findByNomDocument(ressource.getNomDocument()).isEmpty()) {
            ressourceDao.save(ressource);
            return ressource;
        }
        else return null;
    }

    public List<Ressource> getAllRessources() {
        List<Ressource> ressources= new ArrayList<Ressource>();
        ressourceDao.findAll().forEach(ressources::add);
        return ressources;
    }

    public Optional<Ressource> getRessource(Long id){
        return ressourceDao.findById(id);
    }

    public void deleteRessource(Long id) {
        ressourceDao.deleteById(id);
    }

    public ResponseEntity<Ressource> updateRessource(Long ressourceID, Ressource ressource) throws ResourceNotFoundException {
        Ressource ressource1=this.ressourceDao.findById(ressourceID)
                .orElseThrow(() -> new ResourceNotFoundException("aucun fichier n'est trouvé"));
        ressource1.setDocument(ressource.getDocument());
        ressource1.setNomDocument(ressource.getNomDocument());
        Ressource ressourceUpdate=this.ressourceDao.save(ressource1);
        return ResponseEntity.ok(ressourceUpdate);
    }

}
