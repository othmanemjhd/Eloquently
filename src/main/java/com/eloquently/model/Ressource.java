package com.eloquently.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRessource;
    @Column(nullable = false)
    private File document;
    @Column(nullable = false)
    private String nomDocument;
    @ManyToOne @JoinColumn(nullable=false)
    private Createur createur1;
    @ManyToOne @JoinColumn(nullable=false)
    private Abonnee abonnee;
    public Ressource(File file,String nomDocument) {
        this.document=file;
    }
}
