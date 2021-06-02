package com.eloquently.controllers;

import com.eloquently.model.Abonnee;
import com.eloquently.model.Sessionn;
import com.eloquently.services.AbonneeService;
import com.eloquently.services.CoursServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CoursController {

    @Autowired
    private CoursServices coursServices;
    @Autowired
    private AbonneeService abonneeService;

    @GetMapping("/createurs/{emailCreateur}/sessions")
    @ApiOperation(value = "Lister  les sessions d'un createur",
            response = Sessionn.class)
        public List<Sessionn> getAllCours(@PathVariable String emailCreateur){
        return coursServices.getAllSession(emailCreateur);

    }

    @PostMapping("/createurs/{emailCreateur}/sessions")
    @ApiOperation(value = "ajouter une session par un createur",
            response =Sessionn.class)
    public Sessionn addCours(@PathVariable String emailCreateur, @Valid @RequestBody Sessionn cours){
        return coursServices.addSession(cours,emailCreateur);
    }

    /*@RequestMapping(value = "/sessions/", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get all cours ")
    List<Sessionn> getAllCours() {return coursServices.getAllSessions();}*/

    @RequestMapping(value = "/sessions/{emailCreateur}/sessions/{idSession}/abonnees", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get all abonnees d'une session")
    List<Abonnee> getAllAbonneesSession(@PathVariable Long idSession) {return coursServices.getAllAbonneesSession(idSession);}

    @RequestMapping(method = RequestMethod.DELETE,value = ("/createur/{emailCreateur}/sessions/{idSession}"))
    public void DeleteSession(@PathVariable Long idSession) {
        this.coursServices.deleteSession(idSession);
    }


    @PutMapping("/abonnees/{emailAbonnee}/sessions")
    public void abonnementSession(@PathVariable String emailAbonnee,@Valid @RequestBody Sessionn session){
       this.coursServices.abonnementSession(session,emailAbonnee);
    }
    @RequestMapping(method = RequestMethod.DELETE,value ="/abonnees/{emailAbonnee}/sessions")
    public void desabonnementSession(@PathVariable String emailAbonnee,@Valid @RequestBody Sessionn session){
        this.coursServices.desabonnemenrSession(session,emailAbonnee);
    }



}
