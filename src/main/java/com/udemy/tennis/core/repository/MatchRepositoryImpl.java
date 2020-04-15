package com.udemy.tennis.core.repository;



import com.udemy.tennis.core.HibernateUtil;

import com.udemy.tennis.core.entity.Match;
import org.hibernate.Session;


public class MatchRepositoryImpl {

 public void create(Match match) {
     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
     session.persist(match);
     System.out.println("Match ajouté en BDD");
 }
 public Match getByID(Long id) {
        Match match=null;
        Session session=null;
        session= HibernateUtil.getSessionFactory().getCurrentSession();
        match = session.get(Match.class, id);
        System.out.println("Match lu en BDD");
        return match;
    }
 public void delete(Long id) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Match match=session.get(Match.class,id);
        session.delete(match);
        System.out.println("Tournoi supprimé");

    }
}
