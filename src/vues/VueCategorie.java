package vues;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import modele.Facade;

import java.util.Arrays;
import java.util.List;

public class VueCategorie implements Observateur {

    @FXML private Button ButtonPlat;
    @FXML private Button ButtonAccompagnement;
    @FXML private Button ButtonDessert;
    @FXML private Button ButtonBoisson;

    private Facade facade;
    private List<Button> tousLesBoutons;

    public VueCategorie(Facade facade){
        this.facade = facade;
        facade.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        tousLesBoutons = Arrays.asList(ButtonPlat, ButtonAccompagnement, ButtonDessert, ButtonBoisson);
    }

    public void setCategorieToPlat(MouseEvent mouseEvent) {
        selectionnerCategorie("Plat", ButtonPlat);
    }

    public void setCategorieToAccompagnement(MouseEvent mouseEvent) {
        selectionnerCategorie("Accompagnement", ButtonAccompagnement);
    }

    public void setCategorieToDessert(MouseEvent mouseEvent) {
        selectionnerCategorie("Dessert", ButtonDessert);
    }

    public void setCategorieToBoisson(MouseEvent mouseEvent) {
        selectionnerCategorie("Boisson", ButtonBoisson);
    }

    private void selectionnerCategorie(String nomCategorie, Button boutonActif) {
        facade.setCategorieSelectionnee(nomCategorie);
        for (Button b : tousLesBoutons) {
            b.getStyleClass().remove("selected");
        }
        boutonActif.getStyleClass().add("selected"); // seul le button actif est colore.
    }

    @Override
    public void reagir() {

    }
}
