package com.udemy.tennis.controller;

import com.udemy.tennis.core.dto.EpreuveFullDto;
import com.udemy.tennis.core.dto.EpreuveLightDto;
import com.udemy.tennis.core.entity.Epreuve;
import com.udemy.tennis.core.entity.Score;
import com.udemy.tennis.core.service.EpreuveService;
import com.udemy.tennis.core.service.ScoreService;

import java.util.Scanner;

public class EpreuveController {

     private EpreuveService epreuveService;
    public EpreuveController(){
        this.epreuveService= new EpreuveService();
    }

    public void afficheDerniereEpreuve(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID de l'epreuve a affiché?");
        Long id=sc.nextLong();
        EpreuveFullDto epreuve=epreuveService.getEpreuveAvecTournoi(id);
        System.out.println("Le nom du tournoi est "+epreuve.getTournoi().getNom());
    }

    public void afficheRolandGarros(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID de l'epreuve a affiché?");
        Long id=sc.nextLong();
        EpreuveLightDto epreuve=epreuveService.getEpreuveSansTournoi(id);
    }
}
