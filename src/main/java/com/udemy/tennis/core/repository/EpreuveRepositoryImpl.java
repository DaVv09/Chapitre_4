package com.udemy.tennis.core.repository;

import com.udemy.tennis.core.EntityManagerHolder;
import com.udemy.tennis.core.entity.Epreuve;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EpreuveRepositoryImpl {

 public Epreuve getByID(Long id) {
      //Session  session=HibernateUtil.getSessionFactory().getCurrentSession(); hibernate
        EntityManager em= new EntityManagerHolder().getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class, id);
        System.out.println("Epreuve  lu en BDD");
        return epreuve;
    }
    public List<Epreuve> listeEpreuve(String codeTournoi){
        //Session session=HibernateUtil.getSessionFactory().getCurrentSession();hibernate
        EntityManager em= new EntityManagerHolder().getCurrentEntityManager();
        TypedQuery<Epreuve> query=em.createQuery("select e from Epreuve e where e.tournoi.code=?0",Epreuve.class);
        query.setParameter(0,codeTournoi);
        List<Epreuve> listeEpreueves= query.getResultList();
        System.out.println("table epreuve lu");
        return listeEpreueves;
    }
}
