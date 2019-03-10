package traitement;

import contrat.Competence;
import contrat.Enseignant;
import contrat.Etudiant;
import contrat.Stage;
import exceptions.StageException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class StagesRequetes {

    private final StagesIO io;

    public StagesRequetes(StagesIO io) {

        this.io = io;
    }

    /**
     * Renvoie l'ensemble des étudiants de l'enseignant dont le nom est donné en paramètre.
     * @param nom le nom de l'enseignant
     * @return l'ensemble de ses étudiants
     */
    public Set<contrat.Etudiant> etudiantsDeLEnseignant(String nom) {
     /*   Set<contrat.Etudiant> e = new HashSet<>();
        List<Etudiant> etu = this.io.getEtudiants();
        if (etu == null)
        {
            return null;
        }
        else
        for (Etudiant etudiant : etu) {
            if (etudiant.getTuteur().getNom().equals(nom)) {
                e.add(etudiant); // ajout des etudiants dont le tuteur a bien le nom en parametre
            }
        }
        return e;*/
        contrat.Enseignant e = (Enseignant) io.getEnseignants().stream().filter(x -> x.getNom().equals(nom)).findAny().get();
        return e.getEtudiants();
    }

    /**
     * Renvoie les enseignants qui encadrent des stages d'une compétence donnée en paramètre.
     *
     * @param comp la compétence des stages dont on cherche l'encadrant
     * @return l'ensemble des enseignants qui encadrent des stages de cette compétence
     */
    public Set<contrat.Enseignant> enseignantEncadreCompetence(Competence comp) {
        List<Etudiant> etu = this.io.getEtudiants();
        List<Etudiant> ecompetence = null;
        Set<contrat.Enseignant> tcompetence= null;
        for (Etudiant etudiant : etu)
        {
            for (Competence competence : etudiant.getCompetences())
            {
                if (competence == comp)
                {
                    ecompetence.add(etudiant); // on recupere tous les etudiants ayant la competence passée en parametre
                }
            }
        }
        for (contrat.Etudiant etudiant : ecompetence)
        {
            contrat.Enseignant tuteur = etudiant.getTuteur();
            tcompetence.add(tuteur); // on recupere tous les tuteurs (enseignants) qui encadrent ces competences
        }
        return tcompetence;
    }

    /**
     * Qui sont les étudiants n'ayant pas stage pouvant avoir au moins un stage selon leurs compétences
     * et celle du stage ?
     *
     * @return le mapping entre étudiant n'ayant pas de stage et les stages qu'on peut lui proposer,
     * selon ses compétence
     */
    public Map<Etudiant, Set<Stage>> etudiantsMatchStagesNonAffectes() {
        List <contrat.Etudiant> etudiants = this.io.getEtudiants(); // on recupere les etudiants
        List <contrat.Stage> stages = this.io.getStages();//on recupere les stages
        if (etudiants == null)
        {
            return null;
        }
        if (stages == null)
        {
            return null;
        }
        Map<Etudiant, Set<Stage>> es = null;
        //on cherche les etudiants n'ayant pas de stage
        Set<Stage> s = stages.stream().filter(stage -> stage.getEtudiant() == null).collect(Collectors.toSet());
        for (Etudiant etudiant : etudiants)
        { // pour chaque etudiant n'aynat pas de stage
            Set<Stage> stagesanscomp = s.stream().filter(stage -> stage.getCompetence().equals(etudiant.getCompetences())).collect(Collectors.toSet());
            // on collecte les stages ayant une competence correspondante à celle de l'etudiant
            es.put(etudiant,stagesanscomp); // on met dans la map, l'étudiant et l'ensemble des stages qui peuvent lui correspondre (en fonction de ses competences)
        }
        return es;
    }
}
