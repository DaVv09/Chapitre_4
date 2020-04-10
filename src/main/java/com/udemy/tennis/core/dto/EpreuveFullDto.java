package com.udemy.tennis.core.dto;

public class EpreuveFullDto {
    private Long id;
    private Short annee;
    private TournoiDto tournoi;
    private Character typeEpreuve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public TournoiDto getTournoi() {
        return tournoi;
    }

    public void setTournoi(TournoiDto tournoi) {
        this.tournoi = tournoi;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }
}
