package requestTest;

import contrat.Competence;
import contrat.Etudiant;
import contrat.Niveau;
import javafx.scene.shape.Path;
import model.Enseignant;
import model.Entreprise;
import model.Stage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import traitement.StagesIO;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class StagesRequetes {
     traitement.StagesRequetes sr;
     traitement.StagesIO sio;
     String ETU_FILE = "etudiants.txt";
     String STAGES_FILE = "stages.txt";
     String DONNEES = "donnees";
     Etudiant e = new model.Etudiant("Julien PETIT");
     Enseignant tuteur;
     String name;
     Set<contrat.Etudiant> etudiants = new HashSet<>();
     Set<contrat.Stage> stages = new HashSet<>();
     Entreprise entreprise = new Entreprise("Entreprise WebGourou");
     Stage stage;
    @BeforeMethod
    public void setUp() throws IOException {
        sio= new StagesIO(Paths.get(DONNEES, ETU_FILE), Paths.get(DONNEES, STAGES_FILE));
        sr = new traitement.StagesRequetes(sio);
        sio.chargerDonnees();
        this.e.addCompetence(Competence.WEB);
        this.e.addCompetence(Competence.MOBILE);
        this.e.addCompetence(Competence.AMOE);
        this.etudiants.add(this.e);
        stage  = new Stage("S155","Web App Awesome", Competence.WEB, Niveau.L3,entreprise);
        this.e.addStage(stage);
        this.tuteur = new Enseignant("Tuteur Alpha");
        e.setTuteur(tuteur);
    }
    @AfterMethod
    public void tearDown() {
        sr = null;
        sio = null;
        name = null;
    }
/*
    @Parameters({"rachida"})
    @Test
    public void testEtudiantDeLEnseignant(String name)
    {
        assertEquals(sr.etudiantsDeLEnseignant(name),null); //assertequals(ce que j'envoie, ce que j'attends)
    }*/

    @Parameters({"Tuteur Alpha"})
    @Test
    public void testEtudiantDeLEnseignant(String p1) {
        assertEquals(sr.etudiantsDeLEnseignant(p1),this.etudiants); // ce que le prgm retourne et ce que j'attends
    }

}