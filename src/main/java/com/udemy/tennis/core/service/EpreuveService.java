package com.udemy.tennis.core.service;

import com.udemy.tennis.core.EntityManagerHolder;
import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.EpreuveFullDto;
import com.udemy.tennis.core.dto.EpreuveLightDto;
import com.udemy.tennis.core.dto.JoueurDto;
import com.udemy.tennis.core.dto.TournoiDto;
import com.udemy.tennis.core.entity.Epreuve;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EpreuveService {

    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDto getEpreuveDetaillee(Long id) {
        //Session session = null;
        //Transaction tx = null;
        EntityManager em=null; // jpa
        EntityTransaction tx = null;//jpa
        Epreuve epreuve=null;
        EpreuveFullDto epreuveDto=null;
        try {
            em=new EntityManagerHolder().getCurrentEntityManager(); // jpa
            tx=em.getTransaction();
            tx.begin();
            //session=HibernateUtil.getSessionFactory().getCurrentSession();
            //tx = session.beginTransaction();
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

            epreuveDto.setParticipants(new HashSet<>());
            for (Joueur joueur: epreuve.getParticipants()) {
                final JoueurDto joueurDto=new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setSexe(joueur.getSexe());
                epreuveDto.getParticipants().add(joueurDto);
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
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

    public List<EpreuveFullDto> getEpreuveTable(String codeTournoi){
        Session session = null;
        Transaction tx = null;
        List<EpreuveFullDto> tableEpreuveDto=new ArrayList<>();
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            List<Epreuve> epreuves=epreuveRepository.listeEpreuve(codeTournoi);
            for (Epreuve epreuve:epreuves) {
                final EpreuveFullDto epreuveFullDto=new EpreuveFullDto();
                epreuveFullDto.setId(epreuve.getId());
                epreuveFullDto.setAnnee(epreuve.getAnnee());
                TournoiDto tournoiDto=new TournoiDto();
                tournoiDto.setId(epreuve.getTournoi().getId());
                tournoiDto.setNom(epreuve.getTournoi().getNom());
                tournoiDto.setCode(epreuve.getTournoi().getCode());
                epreuveFullDto.setTournoi(tournoiDto);
                tableEpreuveDto.add(epreuveFullDto);
            }
            tx.commit();
            System.out.println("Joueur crée");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tableEpreuveDto;
    }
}
