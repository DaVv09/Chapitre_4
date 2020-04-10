package com.udemy.tennis.controller;

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
        Score score=scoreService.getScore(id);
        System.out.println("le score des sets est de ");
        System.out.println(score.getSet1());
        System.out.println(score.getSet2());
        if(score.getSet3()!=null) {
            System.out.println(score.getSet3());
        }
        if(score.getSet4()!=null) {
            System.out.println(score.getSet4());
        }
        if(score.getSet5()!=null) {
            System.out.println(score.getSet5());
        }
    }
}
