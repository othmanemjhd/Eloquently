package com.eloquently.service;

import com.eloquently.dao.AbonneeDao;
import com.eloquently.model.Abonnee;
import com.eloquently.services.AbonneeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AbonneeServiceTest {
    @InjectMocks
    private AbonneeService abonneeService;

    @Mock
    private AbonneeDao abonneeDao;

    @Test
    public void addCAbonneeTest(){
        Abonnee abonnee=new Abonnee();
        abonnee.setEmail("lidia@outlook.fr");
        abonnee.setNom("meftah");
        abonnee.setPrenom("lidia");
        abonnee.setPassword("123456");
        abonnee.setNiveau("B2");
        when(abonneeDao.save(abonnee)).thenReturn(abonnee);
        Abonnee abonneeNew=abonneeService.addAbonnee(abonnee);
        assertNotNull(abonneeNew);
        assertEquals(abonnee,abonneeNew);
    }

    @Test
    public void getAbonneeByEmailTest(){
        Abonnee abonnee=new Abonnee();
        abonnee.setEmail("lidia@outlook.fr");
        when(abonneeDao.findByEmail(abonnee.getEmail())).thenReturn(abonnee);
        Abonnee abonneeResultat=abonneeService.getAbonneeByEmail(abonnee.getEmail());
        assertNotNull(abonneeResultat);
        assertThat(abonneeResultat.getEmail()).isSameAs(abonnee.getEmail());
    }

    @Test
    public void deleteAbonneeTest(){
        Abonnee abonnee=new Abonnee();
        abonnee.setEmail("lidia@outlook.fr");
        given(abonneeDao.findByEmail(abonnee.getEmail())).willReturn(null);
        abonneeService.deleteAbonneeByEmail(abonnee.getEmail());
        Abonnee abonneeVeri=abonneeService.getAbonneeByEmail(abonnee.getEmail());
        assertNull(abonneeVeri);
    }
}
