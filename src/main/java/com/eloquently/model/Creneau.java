package com.eloquently.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Creneau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreneau;
    @Column(nullable = false)
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date date;
    @Column(nullable = false)
    private String dateDebut;
    @Column(nullable = false)
    private String dateFin;
    private boolean disponibilite;

    @ManyToMany(mappedBy = "creneau_session", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Sessionn> sessions=new ArrayList<Sessionn>();
}
