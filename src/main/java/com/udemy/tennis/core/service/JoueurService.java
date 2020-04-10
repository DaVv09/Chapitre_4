package com.udemy.tennis.core.service;

import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
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
