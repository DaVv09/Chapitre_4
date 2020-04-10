package com.udemy.tennis.controller;

import com.udemy.tennis.core.dto.TournoiDto;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.entity.Tournoi;
import com.udemy.tennis.core.service.JoueurService;
import com.udemy.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {

    private TournoiService tournoiService;
    public TournoiController(){
        this.tournoiService= new TournoiService();
    }

    public void afficheDetailsTournoi(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du tournoi a affiché?");
        Long id=sc.nextLong();
        TournoiDto tournoi=tournoiService.getTournoi(id);//--------------------------------------------------
        System.out.println(tournoi.getNom()+" "+tournoi.getCode());
    }
    public void creerTournoi(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est le nom du Tournoi?");
        String nom=sc.nextLine();
        System.out.println("Quel est le code du tournoi?");
        String code=sc.nextLine();
        TournoiDto tournoiDto=new TournoiDto();
        tournoiDto.setNom(nom);
        tournoiDto.setCode(code);
        tournoiService.createTournoi(tournoiDto);//----------------------------------------------
        System.out.println("Le joueur à été créer, son identifiant est "+tournoiDto.getId());
    }

    public void supprimeTournoi(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du tournoi à supprimer?");
        Long id=sc.nextLong();
        sc.nextLine();//saut de ligne consommé
        tournoiService.delete(id);
    }
}
