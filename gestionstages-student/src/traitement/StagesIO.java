package traitement;

import contrat.*;
import model.Entreprise;
import model.Etudiant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

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
        this.classesMap =new HashMap<>();
        this.enseignantsMap = new HashMap<>();
    }

    /**
     * Charge les données étudiants + stages des fichiers specifiés en paramètres au constructeur de cette classe.
     * @throws IOException
     */
    public void chargerDonnees() throws IOException {
        FileReader fr = new FileReader(String.valueOf(etuFilePath));
        BufferedReader br = new BufferedReader(fr);
        String s = br.readLine();
        Scanner sc = new Scanner(new File(String.valueOf(etuFilePath)));
        sc.useDelimiter("#");

        FileReader frs = new FileReader(String.valueOf(stagesFilePath));
        BufferedReader brs = new BufferedReader(frs);
        String z = brs.readLine();
        Scanner scan = new Scanner(new File(String.valueOf(stagesFilePath)));
        sc.useDelimiter("#");

        while(scan.hasNext()){
            String id = scan.nextLine();
            String titre = scan.nextLine();
            String competence = scan.nextLine();
            String niveau = scan.nextLine();
            String entreprise = scan.nextLine();
            String statut = scan.nextLine();
            stagesMap.put(id, new model.Stage(id, titre, Competence.valueOf(competence), Niveau.valueOf(niveau), new Entreprise(entreprise)));
        }

        while(sc.hasNext()){
            String nom = sc.nextLine();
            String classe = sc.nextLine();
            String filiere = sc.nextLine();
            String annee = sc.nextLine();
            String competence = sc.nextLine();
            String stage = sc.nextLine();
            String tuteur = sc.nextLine();

            etusMap.put(nom, new Etudiant(nom));
        }


    }

    /**
     * Renvoie la liste des classes, triées selon le niveau (L3, M1, M2)
     * @return la liste des classes
     */
    public List<contrat.Classe> getClasses() {
        return null;
    }

    /**
     * Renvoie la liste des enseignants, triés selon leur nom.
     * @return la liste des enseignants
     */
    public List<contrat.Enseignant> getEnseignants(){
        List<contrat.Enseignant> liste = new ArrayList<Enseignant>(SortedMap.values());
        return liste.sort(contrat.Etudiant);
    }

    public Set<contrat.Entreprise> getEntreprises(){
        return null;
    }

    /**
     * Renvoie la liste des étudiants, triés selon leur nom.
     * @return la liste des étudiants
     */
    public List<contrat.Etudiant> getEtudiants(){
        return null;
    }

    /**
     * Renvoie la liste des stages, triés selon le niveau (L3, M1, M2)
     * @return la liste des stages
     */
    public List<contrat.Stage> getStages(){
        return null;
    }

}
