package com.udemy.tennis.core.service;

import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.*;
import com.udemy.tennis.core.entity.Score;
import com.udemy.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepositoryImpl scoreRepository;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public ScoreFullDto getScore(Long id) {
        Session session = null;
        Transaction tx = null;
        Score score=null;
        ScoreFullDto scoreFullDto=null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepository.getByID(id);
            scoreFullDto=new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());
            scoreFullDto.setSet2(score.getSet2());
            scoreFullDto.setSet3(score.getSet3());
            scoreFullDto.setSet4(score.getSet4());
            scoreFullDto.setSet5(score.getSet5());

            MatchDto matchDto=new MatchDto();
            matchDto.setId(score.getMatch().getId());

            scoreFullDto.setMatch(matchDto);

            EpreuveFullDto epreuveDto=new EpreuveFullDto();
            epreuveDto.setId(score.getMatch().getEpreuve().getId());
            epreuveDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());

            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());

            epreuveDto.setTournoi(tournoiDto);
            matchDto.setEpreuve(epreuveDto);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
          return scoreFullDto ;
    }

    public void deleteScore(Long id){
        Session session = null;
        Transaction tx = null;
        Score score=null;
        ScoreFullDto scoreFullDto=null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            scoreRepository.delete(id);
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
