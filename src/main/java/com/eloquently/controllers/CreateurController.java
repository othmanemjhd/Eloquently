package com.eloquently.controllers;

import com.eloquently.model.Createur;
import com.eloquently.services.CreateurService;
import io.swagger.annotations.ApiOperation;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CreateurController {
    @Autowired
    CreateurService createurService;

    @RequestMapping(value = "/createurs", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get all creators")
    List<Createur> getAllCreateurs() {
        return this.createurService.getAllCreateurs();
    }

    @RequestMapping(value = "/createurs", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add a creator")
    String addCreateur(@Valid @RequestBody Createur createur) {
        Createur createurR = this.createurService.addCreateur(createur);
        if (createurR != null)
            return "le createur a ete bien ajouté a la bdd";
        else return "un createur existe deja avec cette adresses email";
    }

    @PutMapping("/createurs/{id}")
    public ResponseEntity<Createur> updateCreateur(@PathVariable(value = "id") Long createurId,
                                                   @Valid @RequestBody Createur createur) throws ResourceNotFoundException {
        return this.createurService.updateCreateur(createurId, createur);
    }

    @RequestMapping(value = "/createurs/{emailCreateur}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get creator by email")
    Createur getCreateur(@PathVariable String emailCreateur) {
        return createurService.getCreateurByEmail(emailCreateur);
    }
    @RequestMapping(method = RequestMethod.DELETE,value = ("/createurs/{emailCreateur}"))

    public void deleteCreateur(@PathVariable String emailCreateur){
        createurService.deleteCreateurByEmail(emailCreateur);
    }


}
