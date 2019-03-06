package model;

import contrat.Etudiant;

import java.util.Set;

public final class Enseignant implements contrat.Enseignant {

    private final String nom;
    private final Set<contrat.Etudiant> etudiants;

    public Enseignant(String nom, Set<contrat.Etudiant> e) {
        this.nom = nom;
        this.etudiants = e;
    }


    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public Set<Etudiant> getEtudiants() {
        return this.etudiants;
    }

    @Override
    public boolean addEtudiant(Etudiant etu) {
        return etudiants.add(etu);
    }
}
