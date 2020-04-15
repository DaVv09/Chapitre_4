package com.udemy.tennis.core.service;

import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.*;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.entity.Match;
import com.udemy.tennis.core.entity.Score;
import com.udemy.tennis.core.entity.Tournoi;
import com.udemy.tennis.core.repository.EpreuveRepositoryImpl;
import com.udemy.tennis.core.repository.JoueurRepositoryImpl;
import com.udemy.tennis.core.repository.MatchRepositoryImpl;
import com.udemy.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;

    public MatchService(){
        this.scoreRepository=new ScoreRepositoryImpl();
        this.matchRepository=new MatchRepositoryImpl();
        this.epreuveRepository=new EpreuveRepositoryImpl();
        this.joueurRepository=new JoueurRepositoryImpl();

      }

    public void enregisterNouveauMatch(Match match){
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
        //matchDAO.createMatchWithScore(match);  requete creation match+ score groupé DAO
    }

    public MatchDto getMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match=null;
        MatchDto matchDto=null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getByID(id);
            matchDto=new MatchDto();
            matchDto.setId(match.getId());

            JoueurDto finalisteDto=new JoueurDto();
            finalisteDto.setId(match.getFinaliste().getId());
            finalisteDto.setNom(match.getFinaliste().getNom());
            finalisteDto.setPrenom(match.getFinaliste().getPrenom());
            finalisteDto.setSexe(match.getFinaliste().getSexe());

            matchDto.setFinaliste(finalisteDto);

            JoueurDto vainqueurDto=new JoueurDto();
            vainqueurDto.setId(match.getVainqueur().getId());
            vainqueurDto.setNom(match.getVainqueur().getNom());
            vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDto.setSexe(match.getVainqueur().getSexe());

            matchDto.setVainqueur(vainqueurDto);

            EpreuveFullDto epreuveDto=new EpreuveFullDto();
            epreuveDto.setId(match.getEpreuve().getId());
            epreuveDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());

            epreuveDto.setTournoi(tournoiDto);

            matchDto.setEpreuve(epreuveDto);

            ScoreFullDto scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(match.getScore().getId());
            scoreFullDto.setSet1(match.getScore().getSet1());
            scoreFullDto.setSet2(match.getScore().getSet2());
            scoreFullDto.setSet3(match.getScore().getSet3());
            scoreFullDto.setSet4(match.getScore().getSet4());
            scoreFullDto.setSet5(match.getScore().getSet5());

            matchDto.setScore(scoreFullDto);
            scoreFullDto.setMatch(matchDto);

            tx.commit();
            System.out.println("match affiché");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return matchDto;
    }

    public void tapisVert(Long id){
        Session session = null;
        Transaction tx = null;
        Match match=null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match=matchRepository.getByID(id);
            Joueur ancienVainqueur=match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte)0);
            match.getScore().setSet2((byte)0);
            match.getScore().setSet3((byte)0);
            match.getScore().setSet4((byte)0);
            match.getScore().setSet5((byte)0);


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

    public void createMatch(MatchDto matchDto){
        Session session = null;
        Transaction tx = null;
        Match match=null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match=new Match();
            match.setEpreuve(epreuveRepository.getByID(matchDto.getEpreuve().getId()));
            match.setFinaliste(joueurRepository.getByID(matchDto.getFinaliste().getId()));
            match.setVainqueur(joueurRepository.getByID(matchDto.getVainqueur().getId()));

             Score score=new Score();
             score.setMatch(match);
             match.setScore(score);
             score.setSet1(matchDto.getScore().getSet1());
             score.setSet2(matchDto.getScore().getSet2());
             score.setSet3(matchDto.getScore().getSet3());
             score.setSet4(matchDto.getScore().getSet4());
             score.setSet5(matchDto.getScore().getSet5());

             matchRepository.create(match);
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

    public void deleteMatch(Long id){
        Session session = null;
        Transaction tx = null;
        Match match=null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            matchRepository.delete(id);

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
