package com.udemy.tennis.core.service;

import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.dto.EpreuveFullDto;
import com.udemy.tennis.core.dto.JoueurDto;
import com.udemy.tennis.core.dto.MatchDto;
import com.udemy.tennis.core.dto.TournoiDto;
import com.udemy.tennis.core.entity.Match;
import com.udemy.tennis.core.entity.Tournoi;
import com.udemy.tennis.core.repository.MatchRepositoryImpl;
import com.udemy.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;

    public MatchService(){
        this.scoreRepository=new ScoreRepositoryImpl();
        this.matchRepository=new MatchRepositoryImpl();
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
}
