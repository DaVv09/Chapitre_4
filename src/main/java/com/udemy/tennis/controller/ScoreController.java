package com.udemy.tennis.controller;

import com.udemy.tennis.core.dto.ScoreFullDto;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.entity.Score;
import com.udemy.tennis.core.service.JoueurService;
import com.udemy.tennis.core.service.ScoreService;

import java.util.Scanner;

public class ScoreController {

     private ScoreService scoreService;
    public ScoreController(){
        this.scoreService= new ScoreService();
    }

    public void afficheDetailsScore(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du score a affiché?");
        Long id=sc.nextLong();
        ScoreFullDto scoreFullDto=scoreService.getScore(id);
        String nomTournoi=scoreFullDto.getMatch().getEpreuve().getTournoi().getNom();// nom du tournoi
        Short anneeEpreuve=scoreFullDto.getMatch().getEpreuve().getAnnee();// année de l'epreuve
        char typeEpreuve=scoreFullDto.getMatch().getEpreuve().getTypeEpreuve();//type epreuve
        System.out.println("le score pour le match de categorie "+(typeEpreuve== 'h' ? "Homme" : "Femme")+" de "+nomTournoi+" "+anneeEpreuve);
        System.out.println(scoreFullDto.getSet1());
        System.out.println(scoreFullDto.getSet2());
        System.out.println(scoreFullDto.getSet3());
        System.out.println(scoreFullDto.getSet4());

    }
}
