package vues;

import javafx.scene.input.MouseEvent;
import modele.Facade;

public class VueCategorie implements Observateur{
    private Facade facade;

    public VueCategorie(Facade facade){
        this.facade = facade ;
        facade.ajouterObservateur(this);
    }
    public void setCategorieToPlat(MouseEvent mouseEvent) {
    }

    public void setCategorieToAccompagnement(MouseEvent mouseEvent) {
    }

    public void setCategorieToDessert(MouseEvent mouseEvent) {
    }

    public void setCategorieToBoisson(MouseEvent mouseEvent) {
    }

    @Override
    public void reagir() {

    }
}
