package com.udemy.tennis.core.service;

import com.udemy.tennis.core.EntityManagerHolder;
import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.JoueurDto;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public List<JoueurDto> getFulljoueurtable(char sexe){
       // Session session = null;
       // Transaction tx = null;
        EntityManager em=null; // jpa
        EntityTransaction tx = null;//jpa
        List<JoueurDto> tableJoueurDto=new ArrayList<>();
        try {
            //session=HibernateUtil.getSessionFactory().getCurrentSession();
            //tx = session.beginTransaction();
            em=new EntityManagerHolder().getCurrentEntityManager(); // jpa
            tx=em.getTransaction();
            tx.begin();
            List<Joueur> joueurs=joueurRepository.tableJoueur(sexe);// variable joueurs de type List(de joueur) issue de la methode TableJoueur de joueurRepo.
            for (Joueur joueur:joueurs) {
                final JoueurDto joueurDto=new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setSexe(joueur.getSexe());
                tableJoueurDto.add(joueurDto);
            }
            tx.commit();
            System.out.println("Joueur crée");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return tableJoueurDto;
    }
    public void createJoueur(Joueur joueur) {
        Session session = null;
        Transaction tx = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.create(joueur);
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
    }
    public Joueur getJoueur(Long id) {
        Session session = null;
        Transaction tx = null;
        Joueur joueur=null;

        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepository.getByID(id);
            tx.commit();
            System.out.println("Joueur affiché");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
          return joueur;
    }
    public void renomme(Long id, String nouveauNom) {
        Joueur joueur=getJoueur(id);
        Session session = null;
        Transaction tx = null;

        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
             tx = session.beginTransaction();

            joueur.setNom(nouveauNom);
            Joueur joueur2=(Joueur)session.merge(joueur);
            tx.commit();
            System.out.println("Joueur renommé");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public void changerSexe(Long id,char nouveauSexe) {
        Joueur joueur=getJoueur(id);
        Session session = null;
        Transaction tx = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur.setSexe(nouveauSexe);
            Joueur joueur2=(Joueur)session.merge(joueur);
            tx.commit();
            System.out.println("sexe joueur modifié");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public void delete(long id){
        Joueur joueur=getJoueur(id);
        Session session = null;
        Transaction tx = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.delete(id);
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
