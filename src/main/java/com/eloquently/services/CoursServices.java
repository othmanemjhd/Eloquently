package com.eloquently.services;

import com.eloquently.dao.AbonneeDao;
import com.eloquently.dao.CreateurDao;
import com.eloquently.dao.SessionDao;
import com.eloquently.model.Abonnee;
import com.eloquently.model.Createur;
import com.eloquently.model.Sessionn;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CoursServices {


    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private CreateurDao createurDao;
    @Autowired
    private AbonneeDao abonneeDao;

    @Transactional
    public Sessionn addSession(Sessionn session,String emailCreateur){
        Createur createur=createurDao.findByEmail(emailCreateur);
        session.setCreateur(createur);
        sessionDao.save(session);
        return session;
    }

    public List<Sessionn> getAllSession(String emailUser) {
        Createur createur=createurDao.findByEmail(emailUser);
        List<Sessionn> listeCours = new ArrayList<Sessionn>();
        listeCours=createur.getSessions();
        return listeCours;
    }

    public Sessionn getSession(Long idSession){
        return this.sessionDao.findById(idSession).get();
    }

    public List<Abonnee> getAllAbonneesSession(Long idSession){
        Sessionn session=this.sessionDao.findById(idSession).get();
        return session.getStudents();
    }

    public List<Sessionn> getAllSessions(){
        List<Sessionn> listeSessions = new ArrayList<Sessionn>();
       this.sessionDao.findAll().forEach(listeSessions::add);
        return listeSessions;
    }
    public void deleteSession(Long idSession){
       sessionDao.deleteById(idSession);
    }

    public ResponseEntity<Sessionn> updateSession(Sessionn session1) throws ResourceNotFoundException {
        Sessionn session=sessionDao.findById(session1.getIdSession())
                .orElseThrow(() -> new ResourceNotFoundException("aucune session avec cet id"));
        session.setNomSession(session1.getNomSession());
        session.setCreneau_session(session1.getCreneau_session());
        session.setCreateur(session1.getCreateur());
        session.setStudents(session1.getStudents());
        session.setNiveauRequis(session1.getNiveauRequis());
        Sessionn sessionR=this.sessionDao.save(session);
        return ResponseEntity.ok(sessionR);
    }

    public String abonnementSession(Sessionn session, String emailAbonnee){
        Abonnee abonnee =abonneeDao.findByEmail(emailAbonnee);
        abonnee.addSession(session);
        abonneeDao.save(abonnee);
        sessionDao.save(session);
        return "abonnement bien fait";
    }

    public String desabonnemenrSession(Sessionn session,String emailAbonnee){
        Abonnee abonnee =abonneeDao.findByEmail(emailAbonnee);
        abonnee.desabonner(session);
        abonnee.addSession(session);
        abonneeDao.save(abonnee);
       // sessionDao.save(session);
        return "desabonnement bien fait";
    }

}
