package model;

import contrat.Entreprise;
import contrat.Etudiant;
import contrat.*;

public final class Stage implements contrat.Stage {
    private final String identifiant;
    private final String titre;
    private final Competence competence;
    private final Niveau niveau;
    private final contrat.Entreprise entreprise;
    private contrat.Etudiant etudiant;
    private Statut statut;

    public Stage(String identifiant, String titre, Competence competence, Niveau niveau,
                 contrat.Entreprise entreprise, Statut statut, contrat.Etudiant etudiant) {
        this.identifiant = identifiant;
        this.titre = titre;
        this.competence = competence;
        this.niveau = niveau;
        this.entreprise = entreprise;
        this.statut = statut;
        this.etudiant = etudiant;
        if(statut==null)
        {
            etudiant=null;
        }
    }

    @Override
    public String getIdentifiant() {
        return this.identifiant;
    }

    @Override
    public Etudiant getEtudiant() {
        return this.etudiant;
    }

    @Override
    public void setEtudiant(Etudiant etudiant) {
    this.etudiant=etudiant;
    }

    @Override
    public String getTitre() {
        return this.titre;
    }

    @Override
    public Competence getCompetence() {
        return this.competence;
    }

    @Override
    public Niveau getNiveau() {
        return this.niveau;
    }

    @Override
    public Entreprise getEntreprise() {
        return this.entreprise;
    }

    @Override
    public Statut getStatut() {
        return this.statut;
    }

    @Override
    public void setStatut(Statut statut) {
        this.statut=statut;
    }
}
