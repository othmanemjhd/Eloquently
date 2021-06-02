package com.eloquently.model;


import enumer.MethodAuth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@PrimaryKeyJoinColumn(name = "idUser")
public class Abonnee extends User {
    @Column(nullable = false)
    private String niveau;

    public Abonnee(String email, MethodAuth facebook) {
    }
    @OneToMany( targetEntity=Ressource.class, mappedBy="abonnee" )
    private List<Ressource> ressources = new ArrayList<>();
    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "session_etud",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "session_id", referencedColumnName = "idSession"))
    private List<Sessionn> session_abonnees;

    public void addSession(Sessionn session) {
        this.getSession_abonnees().add(session);
        session.getStudents().add(this);
    }
    public void desabonner(Sessionn session) {
        this.getSession_abonnees().remove(session);
        session.getStudents().remove(this);
    }
}
