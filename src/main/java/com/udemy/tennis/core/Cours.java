package com.udemy.tennis.core;


import com.udemy.tennis.core.entity.*;

import com.udemy.tennis.core.service.MatchService;


public class Cours {
    public static void main(String... args){
        MatchService matchService=new MatchService();
        Match match=new Match();
        Score score=new Score();
        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);
        match.setScore(score);
        score.setMatch(match);
        Joueur federer=new Joueur();
        Joueur murray=new Joueur();
        federer.setId(32L);
        murray.setId(34L);
        match.setVainqueur(federer);//(32) federer 6/3 6/4 7/6 murray (34)
        match.setFinaliste(murray);
        Epreuve epreuve=new Epreuve();
        epreuve.setId(10l);
        match.setEpreuve(epreuve);
        matchService.enregisterNouveauMatch(match);
        }
    }
