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

    public Etudiant(String nom) {
        this.nom = nom;
        this.stages = new HashSet<>();
        this.competences = new ArrayList<>();
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public Set<contrat.Stage> getStages() {
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
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", stages=" + stages +
                ", competences=" + competences +
                ", tuteur=" + tuteur +
                '}';
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
