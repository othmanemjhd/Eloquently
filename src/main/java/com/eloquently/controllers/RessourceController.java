package com.eloquently.controllers;

import com.eloquently.model.Abonnee;
import com.eloquently.model.Ressource;
import com.eloquently.services.RessourcesServices;
import io.swagger.annotations.ApiOperation;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RessourceController {

    @Autowired
    private RessourcesServices ressourcesServices;

    @RequestMapping(value = "/ressources", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get all ressources")
    List<Ressource> getAllRessources() {return ressourcesServices.getAllRessources();}

    @RequestMapping(value = "/ressources/{idRessource}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get ressource by id")
    Optional<Ressource> getRessourceById(@PathVariable Long idRessource) {return ressourcesServices.getRessource(idRessource);}

    @RequestMapping(value = "/ressources", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add a ressource")
    public String addRessource(Ressource ressource){
        Ressource ressourceR=this.ressourcesServices.addRessource(ressource);
        if(ressourceR!=null)
            return "le document a ete bien ajouté a la bdd";
        else return "un docuemnt existe avec le meme nom";
    }

    @PutMapping("/ressources/{id}")
    public ResponseEntity<Ressource> updateRessource(@PathVariable(value = "id") Long ressourceId,
                                                   @Valid @RequestBody Ressource ressource) throws ResourceNotFoundException {
        return this.ressourcesServices.updateRessource(ressourceId,ressource);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = ("/ressources/{id}"))
    @Transactional
    @ApiOperation(value = "supprimer une ressource",
            response = Abonnee.class)
    public void DeleteRessource(@PathVariable Long id) {
        this.ressourcesServices.deleteRessource(id);
    }

}
