package com.eloquently.controllers;

import com.eloquently.model.Creneau;
import com.eloquently.services.CreneauServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CreneauController {

    @Autowired
    private CreneauServices creneauServices;

    @PostMapping("/createurs/{emailCreateur}/sessions/{idSession}/creneaux")
    @ApiOperation(value = "ajouter un creneau pour une session",
            response = Creneau.class)
    public Creneau addSession(@PathVariable Long idSession, @Valid @RequestBody Creneau creneau){
        return creneauServices.addCreneau(creneau,idSession);
    }

    @GetMapping("/createurs/{emailCreateur}/sessions/{idSession}/creneaux")
    @ApiOperation(value = "Lister  les creneaux d'une session",
            response = Creneau.class)
    public List<Creneau> getAllCreneaux(@PathVariable Long idSession){
        return creneauServices.getAllCreneaux(idSession);

    }

    @RequestMapping(method = RequestMethod.DELETE,value = ("/createurs/{emailCreateur}/sessions/{idSession}/creneaux/{idCreneau}"))
    @Transactional
    @ApiOperation(
            value = "Supprimer un creneau par son id",
            response = Creneau.class)
    public void DeleteCreneau(@PathVariable Long idCreneau) {
        this.creneauServices.deleteCreneau(idCreneau);
    }
}
