package com.eloquently.dao;

import com.eloquently.model.Sessionn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionDao extends JpaRepository<Sessionn, Long> {
    List<Sessionn> findCoursByNomSession(String nomSession);
   // void deleteByIdSession(Long idSession);
}
