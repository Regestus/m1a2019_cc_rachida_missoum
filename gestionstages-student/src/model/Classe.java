package model;

import contrat.Etudiant;
import contrat.Filiere;
import contrat.Niveau;

import java.util.Set;

public final class Classe implements contrat.Classe {

    private final Niveau niveau;
    private final Filiere filiere;
    private final String annee;
    private final Set<contrat.Etudiant> etudiants;

    public Classe(Niveau niveau, Filiere filiere, String annee, Set<contrat.Etudiant> e) {
        this.niveau = niveau;
        this.filiere = filiere;
        this.annee = annee;
        this.etudiants = e;
    }

    @Override
    public Niveau getNiveau() { return this.niveau; }

    @Override
    public String getAnnee() {
        return this.annee;
    }

    @Override
    public Filiere getFiliere() {
        return this.filiere;
    }

    @Override
    public Set<Etudiant> getEtudiants() { //Set<E> est une structure de collection qui ne permet pas la duplication ?
        return this.etudiants;
    }

    @Override
    public boolean addEtudiants(Etudiant etu) {
        return etudiants.add(etu);
    }

    @Override
    public boolean removeEtudiant(Etudiant etu) {
        return etudiants.remove(etu);
    }
}
