package com.eloquently.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@PrimaryKeyJoinColumn(name = "idUser")
public class Createur extends User {
    @Column(nullable = false)
    private String grade;
    private URI siteWeb;

   // @JsonIgnore

    @JsonBackReference
    @OneToMany( targetEntity=Sessionn.class, mappedBy="createur" )
    private List<Sessionn> sessions = new ArrayList<>();

    //@JsonBackReference
    @OneToMany( targetEntity=Ressource.class, mappedBy="createur1" )
    private List<Ressource> ressources = new ArrayList<>();


}
