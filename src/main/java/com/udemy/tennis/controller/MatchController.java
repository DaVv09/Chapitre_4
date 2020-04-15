package com.udemy.tennis.controller;

import com.udemy.tennis.core.dto.EpreuveFullDto;
import com.udemy.tennis.core.dto.JoueurDto;
import com.udemy.tennis.core.dto.MatchDto;
import com.udemy.tennis.core.dto.ScoreFullDto;
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
        System.out.println(matchDto.getScore().getSet1());
        System.out.println(matchDto.getScore().getSet2());
        if(matchDto.getScore().getSet3()!=null) {
            System.out.println(matchDto.getScore().getSet3());
        }
        if(matchDto.getScore().getSet4()!=null) {
        System.out.println(matchDto.getScore().getSet4());
        }
            if(matchDto.getScore().getSet5()!=null) {
        System.out.println(matchDto.getScore().getSet5());
            }
    }

    public void tapisVert(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du match a modifier?");
        Long id=sc.nextLong();
        matchService.tapisVert(id);

    }

    public void ajouterMatch(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID de l'epreuve");
        Long epreuveId=sc.nextLong();
        sc.nextLine();
        System.out.println("Quel est l'ID du vainqueur");
        Long vainqueurId=sc.nextLong();
        sc.nextLine();
        System.out.println("Quel est l'ID du finaliste");
        Long finalisteId=sc.nextLong();
        sc.nextLine();
        MatchDto matchDto=new MatchDto();
        matchDto.setEpreuve(new EpreuveFullDto());
        matchDto.getEpreuve().setId(epreuveId);
        matchDto.setVainqueur(new JoueurDto());
        matchDto.getVainqueur().setId(vainqueurId);
        matchDto.setFinaliste(new JoueurDto());
        matchDto.getFinaliste().setId(finalisteId);

        System.out.println("Quel est la valeur du 1er set");
        Byte set1=sc.nextByte();
        sc.nextLine();
        System.out.println("Quel est la valeur du 2eme set");
        Byte set2=sc.nextByte();
        sc.nextLine();
        System.out.println("Quel est la valeur du 3eme set");
        Byte set3=sc.nextByte();
        sc.nextLine();
        System.out.println("Quel est la valeur du 4eme set");
        Byte set4=sc.nextByte();
        sc.nextLine();
        System.out.println("Quel est la valeur du 5eme set");
        Byte set5=sc.nextByte();
        sc.nextLine();

        ScoreFullDto scoreFullDto=new ScoreFullDto();
        scoreFullDto.setSet1(set1);
        scoreFullDto.setSet2(set2);
        scoreFullDto.setSet3(set3);
        scoreFullDto.setSet4(set4);
        scoreFullDto.setSet5(set5);
        matchDto.setScore(scoreFullDto);
        scoreFullDto.setMatch(matchDto);

        matchService.createMatch(matchDto);
    }

    public void deleteMatch(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du match a supprimer?");
        Long id=sc.nextLong();
        matchService.deleteMatch(id);
    }
}
