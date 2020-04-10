package com.udemy.tennis.core.service;

import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.entity.Score;
import com.udemy.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepositoryImpl scoreRepository;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public Score getScore(Long id) {
        Session session = null;
        Transaction tx = null;
        Score score=null;

        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepository.getByID(id);
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
          return score;
    }
}
