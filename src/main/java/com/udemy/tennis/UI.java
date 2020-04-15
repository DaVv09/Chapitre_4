package com.udemy.tennis;

import com.udemy.tennis.controller.*;
import com.udemy.tennis.core.dto.EpreuveFullDto;

public class UI {
    public static void main(String... args){
         JoueurController controller=new JoueurController();
         controller.afficheListeJoueur();
    }
}
