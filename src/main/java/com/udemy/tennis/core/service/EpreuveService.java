package com.udemy.tennis.core.service;

import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.EpreuveFullDto;
import com.udemy.tennis.core.dto.EpreuveLightDto;
import com.udemy.tennis.core.dto.TournoiDto;
import com.udemy.tennis.core.entity.Epreuve;
import com.udemy.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {

    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDto getEpreuveAvecTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve=null;
        EpreuveFullDto epreuveDto=null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getByID(id);
            Hibernate.unproxy(epreuve.getTournoi());
            tx.commit();
            epreuveDto=new EpreuveFullDto();
            epreuveDto.setId(epreuve.getId());
            epreuveDto.setAnnee(epreuve.getAnnee());
            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            epreuveDto.setTournoi(tournoiDto);
            epreuveDto.setTypeEpreuve(epreuve.getTypeEpreuve());
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return epreuveDto;
    }

    public EpreuveLightDto getEpreuveSansTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve=null;
        EpreuveLightDto epreuveDto=null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getByID(id);
           tx.commit();
            epreuveDto=new EpreuveLightDto();
            epreuveDto.setId(epreuve.getId());
            epreuveDto.setAnnee(epreuve.getAnnee());
            epreuveDto.setTypeEpreuve(epreuve.getTypeEpreuve());
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return epreuveDto;
    }
}
