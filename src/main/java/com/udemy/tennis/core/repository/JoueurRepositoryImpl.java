package com.udemy.tennis.core.repository;


import com.udemy.tennis.core.DataSourceProvider;
import com.udemy.tennis.core.EntityManagerHolder;
import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {


 public void create(Joueur joueur) {
     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
     session.persist(joueur);
 }
 public void delete(Long id) {
        Joueur joueur=getByID(id);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
        System.out.println("Joueur supprimé en BDD");

    }
 public Joueur getByID(Long id) {
        Joueur joueur=null;
        Session session=null;
        //session=HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em= new EntityManagerHolder().getCurrentEntityManager();
        joueur = em.find(Joueur.class, id);
        System.out.println("Joueur lu en BDD");
        return joueur;
    }
 public List<Joueur> tableJoueur(char sexe) {
        //Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em= new EntityManagerHolder().getCurrentEntityManager();
        TypedQuery<Joueur> query=em.createNamedQuery("givenSexe",Joueur.class);
        query.setParameter(0,sexe);
        List<Joueur> tableJoueur= query.getResultList();
        System.out.println("table joueur lu");
        return tableJoueur;
        }
}
