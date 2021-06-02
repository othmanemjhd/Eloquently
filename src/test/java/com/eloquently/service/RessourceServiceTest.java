package com.eloquently.service;

import com.eloquently.ressourceTest.AbonneePrototype;
import com.eloquently.dao.RessourceDao;
import com.eloquently.model.Ressource;
import com.eloquently.services.RessourcesServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RessourceServiceTest {
    @InjectMocks
    private RessourcesServices ressourcesServices;

    @Mock
    private RessourceDao ressourceDao;

    AbonneePrototype abonneePrototype=new AbonneePrototype();

    @Test
    public void addCRessourceByAbonneeTest(){
        Ressource ressource=new Ressource();
        ressource.setDocument(new File("src/test/java/com/projet/jee/projet/ressourceTest/exempleDocument.txt"));
        ressource.setAbonnee(abonneePrototype.pAbonnee());
        ressource.setNomDocument("exemple");
        ressource.setCreateur1(null);
        when(ressourceDao.save(ressource)).thenReturn(ressource);
        Ressource ress=ressourcesServices.addRessource(ressource);
        assertNotNull(ress);
        assertEquals(ress,ressource);
    }

}
