package com.eloquently.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sessionn{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSession;
    @Column(nullable = false)
    private String nomSession;
    @Column(nullable = false)
    private String niveauRequis;
    @ManyToMany(mappedBy = "session_abonnees", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
   private List<Abonnee> students=new ArrayList<Abonnee>();
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "creneau_session",
            joinColumns = @JoinColumn(name = "session_id", referencedColumnName = "idSession"),
            inverseJoinColumns = @JoinColumn(name = "creneau_id", referencedColumnName = "idCreneau"))
    private List<Creneau> creneau_session=new ArrayList<Creneau>();

    @ManyToOne @JoinColumn(nullable=false)
    private Createur createur;
    public void addCreneau(Creneau creneau) {
        this.getCreneau_session().add(creneau);
        creneau.getSessions().add(this);
    }
}
