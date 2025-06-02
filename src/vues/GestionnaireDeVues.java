package vues;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import modele.Facade;
import vues.VueDetails;

import java.io.IOException;

public class GestionnaireDeVues {

    private BorderPane rootPane;
    private Node vueMenu;
    private Node vueGlobale;
    private Node vueCategorie;

    private  Facade facade;

    public GestionnaireDeVues(BorderPane rootPane, Facade facade) {
        this.rootPane = rootPane;
        this.facade = facade;
    }

    public void setVueMenu(Node vueMenu) {
        this.vueMenu = vueMenu;
        rootPane.setTop(vueMenu);
    }

    public void setVueGlobale(Node vueGlobale, Node vueCategorie) {
        this.vueGlobale = vueGlobale;
        this.vueCategorie = vueCategorie;
        HBox center = new HBox(20);
        center.setPadding(new Insets(10));
        center.getChildren().addAll(vueCategorie, vueGlobale) ;
        rootPane.setCenter(center);
    }

    public void afficherVueGlobale() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/VueGlobale.fxml"));
            VueGlobale vueGlobaleController = new VueGlobale(facade, this);
            loader.setController(vueGlobaleController);
            Node vueGlobale = loader.load();

            HBox center = new HBox(20);
            center.setPadding(new Insets(10));
            center.getChildren().addAll(vueCategorie, vueGlobale);
            rootPane.setCenter(center);
        } catch (IOException e) {
            e.printStackTrace(); // Ou afficher une alerte
        }
    }


    public void afficherVueDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/VueDetails.fxml"));
            VueDetails vueDetailsController = new VueDetails(facade, this);
            loader.setController(vueDetailsController);
            Node vueDetails = loader.load();
            HBox center = new HBox(20);
            center.setPadding(new Insets(10));
            center.getChildren().addAll(vueCategorie, vueDetails) ;
            rootPane.setCenter(center);
        } catch (IOException e) {
            e.printStackTrace(); // Ou showAlert
        }
    }
}
