package com.eloquently.controllers;


import com.eloquently.model.Abonnee;
import com.eloquently.services.AbonneeService;
import io.swagger.annotations.ApiOperation;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AbonneeController {
    @Autowired
    private AbonneeService abonneeService;

    @RequestMapping(value = "/abonnees", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get all abonnees")
    List<Abonnee> getAllAbonnees() {
        return this.abonneeService.getAllAbonnees();
    }

    @RequestMapping(value = "/abonnees", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add a abonnee")
    String addCreateur(@RequestBody Abonnee abonnee){
        Abonnee abonnee1=this.abonneeService.addAbonnee(abonnee);
        if(abonnee1!=null)
            return "l'abonnee a ete bien ajouté a la bdd";
        else return "un abonnee existe deja avec cette adresse mail";
    }

    @PutMapping("/abonnees")
    public ResponseEntity<Abonnee> updateAbonnee(
                                                 @Valid @RequestBody Abonnee abonnee) throws ResourceNotFoundException {
        return this.abonneeService.updateAbonnee(abonnee);
    }

    @RequestMapping(value = "/abonnees/{emailAbonnee}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get abonnee by email")
    Abonnee getAbonnee(@PathVariable String emailAbonnee) {return abonneeService.getAbonneeByEmail(emailAbonnee);}


}
