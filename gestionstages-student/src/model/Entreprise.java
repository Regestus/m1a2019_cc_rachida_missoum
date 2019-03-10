package model;

import java.util.HashSet;
import java.util.Set;

public final class Entreprise implements contrat.Entreprise {

    private final String nom;
    private final Set<contrat.Stage> stages;

    @Override
    public String toString() {
        return "Entreprise{" +
                "nom='" + nom + '\'' +
                ", stages=" + stages +
                '}';
    }

    public Entreprise(String nom) {
        this.nom = nom;
        this.stages = new HashSet<contrat.Stage>();
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
    public boolean addStage(Stage stage) {
        return false;
    }
}
