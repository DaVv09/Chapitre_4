package com.udemy.tennis.core.repository;

import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.entity.Epreuve;
import org.hibernate.Session;

public class EpreuveRepositoryImpl {

 public Epreuve getByID(Long id) {
      Session  session=HibernateUtil.getSessionFactory().getCurrentSession();
      Epreuve epreuve = session.get(Epreuve.class, id);
        System.out.println("Epreuve lu");
        return epreuve;
    }

}
