package com.udemy.tennis.controller;

import com.udemy.tennis.core.dto.MatchDto;
import com.udemy.tennis.core.service.MatchService;
import java.util.Scanner;

public class MatchController {

     private MatchService matchService;
    public MatchController(){
        this.matchService= new MatchService();
    }

    public void afficheDetailsMatch(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du match a affiché?");
        Long id=sc.nextLong();
        MatchDto matchDto=matchService.getMatch(id);
        System.out.println(matchDto.getVainqueur().getNom()+" "+matchDto.getVainqueur().getPrenom()+" est le vainqueur de "+matchDto.getEpreuve().getTournoi().getNom()+" "+ matchDto.getEpreuve().getAnnee());
        System.out.println(matchDto.getFinaliste().getNom()+" "+matchDto.getFinaliste().getPrenom()+" est le finaliste de "+matchDto.getEpreuve().getTournoi().getNom()+" "+ matchDto.getEpreuve().getAnnee());
    }
}
