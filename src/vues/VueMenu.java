package vues;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import modele.Facade;


public class VueMenu implements Observateur{
    private Facade facade;

    @FXML
    private MenuItem modeEditionItem;
    public VueMenu(Facade facade){
        this.facade = facade ;
        facade.ajouterObservateur(this);
    }
    public void importerJson(ActionEvent actionEvent) {
    }

    public void SauvegarderJson(ActionEvent actionEvent) {
    }

    public void quitter(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void modeConsultation(ActionEvent actionEvent) {
    }

    public void modeEdition(ActionEvent actionEvent) {
    }

    public void trierParNom(ActionEvent actionEvent) {
    }

    public void trierParPrix(ActionEvent actionEvent) {
    }

    @Override
    public void reagir() {

    }
}
