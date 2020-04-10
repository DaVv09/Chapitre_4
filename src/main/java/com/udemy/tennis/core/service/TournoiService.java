package com.udemy.tennis.core.service;

import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.TournoiDto;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.entity.Tournoi;
import com.udemy.tennis.core.repository.JoueurRepositoryImpl;
import com.udemy.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void createTournoi(TournoiDto tournoiDto) {
        Session session = null;
        Transaction tx = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Tournoi tournoi=new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setNom(tournoiDto.getNom());
            tournoi.setCode(tournoiDto.getCode());
            tournoiRepository.create(tournoi);
            tx.commit();
            System.out.println("Tournoi crée");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public TournoiDto getTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        Tournoi tournoi=null;
        TournoiDto tournoiDto=null;

        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoi = tournoiRepository.getByID(id);
            tournoiDto=new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setNom(tournoi.getNom());
            tournoiDto.setCode(tournoi.getCode());
            tx.commit();
            System.out.println("tournoi affiché");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
          return tournoiDto;
    }
    public void delete(long id){
        Session session = null;
        Transaction tx = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepository.delete(id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
}
