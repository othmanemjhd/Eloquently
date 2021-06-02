package com.eloquently.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommentaire;
    @Column(nullable = false)
    private String commentaire;
    private Integer score;
    @ManyToOne
    private Createur createur;
    @ManyToOne
    private Abonnee abonnee;
    @ManyToOne
    private Sessionn session;
}
