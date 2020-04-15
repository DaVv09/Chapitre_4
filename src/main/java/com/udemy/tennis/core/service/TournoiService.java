package com.udemy.tennis.core.service;

import com.udemy.tennis.core.EntityManagerHolder;
import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.TournoiDto;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.entity.Tournoi;
import com.udemy.tennis.core.repository.JoueurRepositoryImpl;
import com.udemy.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void createTournoi(TournoiDto tournoiDto) {
        EntityManager em=null; // jpa
        EntityTransaction tx = null;//jpa
        // Session session = null;
        //Transaction tx = null;
        try {
           // session=HibernateUtil.getSessionFactory().getCurrentSession();
            //tx = session.beginTransaction();

            em=new EntityManagerHolder().getCurrentEntityManager(); // jpa
            tx=em.getTransaction();
            tx.begin();
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
            if (em != null) {
                em.close();
            }
        }
    }
    public TournoiDto getTournoi(Long id) {
       //Session session = null;
        EntityManager em=null; // jpa
        EntityTransaction tx = null;
        Tournoi tournoi=null;
        TournoiDto tournoiDto=null;

        try {
           // session=HibernateUtil.getSessionFactory().getCurrentSession();
            //tx = session.beginTransaction();
            em=new EntityManagerHolder().getCurrentEntityManager(); // jpa

            tx=em.getTransaction();
            tx.begin();

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
            if (em != null) {
                em.close();
            }
        }
          return tournoiDto;
    }
    public void delete(long id){
        EntityManager em=null; // jpa
        EntityTransaction tx = null;//jpa
       // Session session = null;
        //Transaction tx = null;
        try {
            //session=HibernateUtil.getSessionFactory().getCurrentSession();
            // tx = session.beginTransaction();
            em=new EntityManagerHolder().getCurrentEntityManager(); // jpa

            tx=em.getTransaction();
            tx.begin();
            tournoiRepository.delete(id);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
}
