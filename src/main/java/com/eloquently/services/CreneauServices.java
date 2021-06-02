package com.eloquently.services;

import com.eloquently.dao.CreneauDao;
import com.eloquently.dao.SessionDao;
import com.eloquently.model.Creneau;
import com.eloquently.model.Sessionn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreneauServices {
    @Autowired
    private CreneauDao creneauDao;
    @Autowired
    private SessionDao sessionDao;

    @Transactional
    public Creneau addCreneau(Creneau creneau, Long idSession){
        Optional<Sessionn> session=sessionDao.findById(idSession);
        session.get().addCreneau(creneau);
        return creneau;
    }

    public List<Creneau> getAllCreneaux(Long idSession) {
        Optional<Sessionn> session=sessionDao.findById(idSession);
        List<Creneau> listeCreneaux = new ArrayList<Creneau>();
        listeCreneaux=session.get().getCreneau_session();
        return listeCreneaux;
    }

    public void deleteCreneau(Long idCreneau){
        creneauDao.deleteByIdCreneau(idCreneau);
    }
}
