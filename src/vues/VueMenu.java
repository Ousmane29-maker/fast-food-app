package vues;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import modele.Categorie;
import modele.Facade;


public class VueMenu implements Observateur{
    private Facade facade;
    private GestionnaireDeVues gestionnaireDeVues;

    public VueMenu(Facade facade, GestionnaireDeVues gestionnaireDeVues){
        this.facade = facade ;
        this.gestionnaireDeVues = gestionnaireDeVues ;
        facade.ajouterObservateur(this);
    }
    public void importerJson(ActionEvent actionEvent) {
    }

    public void SauvegarderJson(ActionEvent actionEvent) {
    }

    public void quitter(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void ajouterArticle(ActionEvent actionEvent) {
        // Crée un nouvel article vide (temporaire)
        facade.ajouterNouvel() ;
        facade.setModeEdition(true); // Active le mode édition
        facade.setModeAjout(true);
        gestionnaireDeVues.afficherVueDetails() ;
    }


    public void trierParNom(ActionEvent actionEvent) {
    }

    public void trierParPrix(ActionEvent actionEvent) {
    }

    @Override
    public void reagir() {

    }
}
