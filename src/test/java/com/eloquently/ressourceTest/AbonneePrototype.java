package com.eloquently.ressourceTest;

import com.eloquently.model.Abonnee;

public class AbonneePrototype {
    public static Abonnee pAbonnee(){
        Abonnee abonnee=new Abonnee();
        abonnee.setEmail("lidia@outlook.fr");
        abonnee.setNom("meftah");
        abonnee.setPrenom("lidia");
        abonnee.setPassword("123456");
        abonnee.setNiveau("B2");
        return abonnee;
    }
}
