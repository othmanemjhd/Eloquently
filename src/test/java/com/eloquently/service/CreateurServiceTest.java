package com.eloquently.service;


import com.eloquently.dao.CreateurDao;
import com.eloquently.model.Createur;
import com.eloquently.services.CreateurService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateurServiceTest {
    @InjectMocks
    private CreateurService createurService;

    @Mock
    private CreateurDao createurDao;

    @Test
    public void addCreateurTest(){
        Createur createur = new Createur();
        createur.setEmail("lidia@outlook.fr");
        createur.setNom("meftah");
        createur.setPrenom("lidia");
        createur.setPassword("123456");
        createur.setGrade("bien");
        when(createurDao.save(createur)).thenReturn(createur);
        Createur createurNew=createurService.addCreateur(createur);
        assertNotNull(createurNew);
        assertEquals(createur,createurNew);
    }

    @Test
    public void getCreateurByEmailTest(){
        Createur createur=new Createur();
        createur.setEmail("lidia@outlook.fr");
        when(createurDao.findByEmail(createur.getEmail())).thenReturn(createur);
       Createur createurResultat=createurService.getCreateurByEmail(createur.getEmail());
       assertNotNull(createurResultat);
       assertThat(createurResultat.getEmail()).isSameAs(createur.getEmail());
    }

    @Test
    public void deleteCreateurTest(){
        Createur createur=new Createur();
        createur.setEmail("lidia@outlook.fr");
        given(createurDao.findByEmail(createur.getEmail())).willReturn(null);
        createurService.deleteCreateurByEmail(createur.getEmail());
        Createur createurVeri=createurService.getCreateurByEmail(createur.getEmail());
        assertNull(createurVeri);
    }
}
