package model;

import contrat.Competence;
import contrat.Enseignant;
import contrat.Stage;

import java.util.*;

public final class Etudiant implements contrat.Etudiant {

    private final String nom;
    private final Set<contrat.Stage> stages;
    private final List<Competence> competences;
    private contrat.Enseignant tuteur;


    public Etudiant(String nom, Set<contrat.Stage> s, List<Competence> c, contrat.Enseignant t) {
        this.nom = nom;
        this.stages = s;
        this.competences = c;
        this.tuteur=t;
    }


    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public Set<Stage> getStages() {
        return this.stages;
    }

    @Override
    public List<Competence> getCompetences() {
        return this.competences;
    }

    @Override
    public boolean addStage(Stage stage) {
        return stages.add(stage);
    }

    @Override
    public boolean addCompetence(Competence competence) {
        return competences.add(competence);
    }

    @Override
    public Enseignant getTuteur() {
        return this.tuteur;
    }

    @Override
    public void setTuteur(Enseignant tuteur) {
        this.tuteur=tuteur;
    }
}
