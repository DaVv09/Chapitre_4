package com.udemy.tennis.controller;

import com.udemy.tennis.core.dto.JoueurDto;
import com.udemy.tennis.core.entity.Joueur;
import com.udemy.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

     private JoueurService joueurService;
    public JoueurController(){
        this.joueurService= new JoueurService();
    }

    public void afficheDetailsJoueur(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du joueur a affiché?");
        Long id=sc.nextLong();
        Joueur joueur=joueurService.getJoueur(id);
        System.out.println(joueur.getPrenom()+" "+joueur.getNom());
    }

    public void creerJoueur(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est le nom du joueur?");
        String nom=sc.nextLine();
        System.out.println("Quel est le prénom du joueur?");
        String prenom=sc.nextLine();
        System.out.println("Quel est le sexe du joueur?");
        char sexe=sc.nextLine().charAt(0);
        Joueur joueur=new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setSexe(sexe);
        joueurService.createJoueur(joueur);
        System.out.println("Le joueur à été créer, son identifiant est "+joueur.getId());
    }

    public void renommeJoueur(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du joueur à modifier?");
        Long id=sc.nextLong();
        sc.nextLine();//saut de ligne consommé
        System.out.println("Quel est le nouveau nom du joueur?");
        String nouveauNom=sc.nextLine();
        joueurService.renomme(id,nouveauNom);
    }

    public void changerSexeJoueur(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du joueur à modifier?");
        Long id=sc.nextLong();
        sc.nextLine();//saut de ligne consommé
        System.out.println("Quel est le nouveau sexe du joueur?");
        char nouveauSexe=sc.nextLine().charAt(0);
        joueurService.changerSexe(id,nouveauSexe);
    }

    public void supprimeJoueur(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel est l'ID du joueur à supprimer?");
        Long id=sc.nextLong();
        sc.nextLine();//saut de ligne consommé
       joueurService.delete(id);
    }

    public void afficheListeJoueur(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quel liste de joueur voulez vous afficher (F)emme ou (H)omme ?");
        char sexe=sc.nextLine().charAt(0);
      for(JoueurDto joueurDto : joueurService.getFulljoueurtable(sexe)){
          System.out.println(joueurDto.getId()+" "+joueurDto.getPrenom()+" "+joueurDto.getNom()+" "+joueurDto.getSexe());
      }

    }
}
