package traitement;

import contrat.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Permet de charger les fichiers contenant les donnees des etudiants et des stages.
 * Parse et donne accès à ces données en mémoire.
 */
public final class StagesIO {

    private final Map<String, contrat.Stage> stagesMap;
    private final Map<String, contrat.Etudiant> etusMap;
    private final Map<Integer, contrat.Classe> classesMap;
    private final Map<String, contrat.Enseignant> enseignantsMap;
    /**
     * Représente le chemin du fichier des données étudiants.
     */
    private Path etuFilePath;
    /**
     * Représente le chemin du fichier des données stages.
     */
    private Path stagesFilePath;


    public StagesIO(Path etuFilePath, Path stagesFilePath) {
        this.etuFilePath = etuFilePath;
        this.stagesFilePath = stagesFilePath;
        this.stagesMap = new HashMap<>();
        this.etusMap = new HashMap<>();
        this.classesMap = new HashMap<>();
        this.enseignantsMap = new HashMap<>();
    }

    /**
     * Charge les données étudiants + stages des fichiers specifiés en paramètres au constructeur de cette classe.
     * @throws IOException
     */
    public void chargerDonnees() throws IOException {
       /* FileReader fr = new FileReader(String.valueOf(stagesFilePath));
        BufferedReader br = new BufferedReader(fr);
        br.readLine();

        Scanner read = new Scanner (new File(String.valueOf(stagesFilePath)));
        read.useDelimiter("#");
        List<contrat.Entreprise> ent = new ArrayList() ;
        while (read.hasNext()){
            String id = read.next();
            String titre = read.next();
            String competence = read.next();
            String niveau = read.next();
            String entreprise = read.next();
            //entreprise recherche

            model.Entreprise e = new model.Entreprise(entreprise);
            model.Stage s = new model.Stage(id, titre, Competence.valueOf(competence),
                    Niveau.valueOf(niveau), e);
            //s.setStatut(Statut.valueOf(statut));
            e.addStage(s);

            stagesMap.put(id, s);
        }

        fr = new FileReader(String.valueOf(etuFilePath));
        br = new BufferedReader(fr);
        br.readLine();

        read = new Scanner (new File(String.valueOf(etuFilePath)));
        read.useDelimiter("#");

        while (read.hasNext()){
            String name = read.next();
            String niveau = read.next();
            String filiere = read.next();
            String annee = read.next();
            String competence = read.next();
            String stage = read.next();
            String tuteur =read.next();
            String tut[] = tuteur.split(" ");

            Etudiant e = new model.Etudiant(name);

            e.addStage(stagesMap.get(stage));
            etusMap.put(name, e);

            Enseignant en = new model.Enseignant(tut[1]);
            e.setTuteur(en);
            en.addEtudiant(e);
            enseignantsMap.put(name, en);

            String competences[] = competence.split(",");
            for (int i=0; i<=competences.length; i++)
            {
                e.addCompetence(Competence.valueOf(competences[i]));
            }
            //classe recherche
            classesMap.put(classesMap.hashCode(),
                    new model.Classe(Niveau.valueOf(niveau), Filiere.valueOf(filiere), annee));
        }*/


        Stream etufile = Files.lines(etuFilePath);
        Stream stagefile = Files.lines(stagesFilePath);
        stagefile.forEach(x -> {
            String[] tab = x.toString().split("#");
            this.stagesMap.put(tab[0], new model.Stage(tab[0],tab[1],Competence.valueOf(tab[2]),Niveau.valueOf(tab[3]),new model.Entreprise(tab[4])));
        });
        etufile.forEach(x -> {
            String[] tab = x.toString().split("#");
            this.etusMap.put(tab[0],new model.Etudiant(tab[0]));
            String[] tab2 = tab[4].toString().split(",");
            for (String c:tab2 ) {
                this.etusMap.get(tab[0]).addCompetence(Competence.valueOf(c));
            }
            this.classesMap.put(Niveau.valueOf(tab[1]).getNiveau(), new model.Classe(Niveau.valueOf(tab[1]), Filiere.valueOf(tab[2]), tab[3]));
            this.enseignantsMap.put(tab[6], new model.Enseignant(tab[6]));
            this.etusMap.get(tab[0]).setTuteur(this.enseignantsMap.get(tab[6]));
            this.classesMap.get(Niveau.valueOf(tab[1]).getNiveau()).addEtudiants(etusMap.get(tab[0]));
            this.enseignantsMap.get(tab[6]).addEtudiant(this.etusMap.get(tab[0]));
            if(this.stagesMap.get(tab[5]) != null)
            {
                this.etusMap.get(tab[0]).addStage(this.stagesMap.get(tab[5]));
            }
        });
    }

    /**
     * Renvoie la liste des classes, triées selon le niveau (L3, M1, M2)
     * @return la liste des classes
     */
    public List<contrat.Classe> getClasses(){
        List<contrat.Classe> classes = this.classesMap.values().stream().sorted(Comparator.comparing(contrat.Classe :: getNiveau)).collect(Collectors.toList());
        return classes;
    }

    /**
     * Renvoie la liste des enseignants, triés selon leur nom.
     * @return la liste des enseignants
     */
    public List<contrat.Enseignant> getEnseignants(){
        List<contrat.Enseignant> enseignants = this.enseignantsMap.values().stream().sorted(Comparator.comparing(contrat.Enseignant :: getNom)).collect(Collectors.toList());
        return enseignants;
    }

    public Set<contrat.Entreprise> getEntreprises(){
            return this.getStages().stream().map(Stage::getEntreprise).collect(Collectors.toSet());
    }

    /**
     * Renvoie la liste des étudiants, triés selon leur nom.
     *
     * @return la liste des étudiants
     */
    public List<contrat.Etudiant> getEtudiants(){
        List<contrat.Etudiant> etudiants = this.etusMap.values().stream().sorted(Comparator.comparing(contrat.Etudiant::getNom)).collect(Collectors.toList());
        return etudiants;
    }

    /**
     * Renvoie la liste des stages, triés selon le niveau (L3, M1, M2)
     * @return la liste des stages
     */
    public List<contrat.Stage> getStages(){
        List<contrat.Stage> stages = this.stagesMap.values().stream().sorted(Comparator.comparing(contrat.Stage::getNiveau)).collect(Collectors.toList());
        return stages;
    }

}
