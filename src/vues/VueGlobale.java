package vues;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import modele.Article;
import modele.Facade;

import java.util.Iterator;

public class VueGlobale implements Observateur {
    @FXML
    private TilePane tilePane ;

    private Facade facade ;
    private GestionnaireDeVues gestionnaireDeVues;

    public VueGlobale(Facade facade, GestionnaireDeVues gestionnaireDeVues){
        this.facade = facade ;
        facade.ajouterObservateur(this);
        this.gestionnaireDeVues = gestionnaireDeVues ;
    }



    @FXML
    public void initialize(){
        reagir();
    }

    @Override
    public void reagir() {
        try {
            tilePane.getChildren().clear();
            Iterator<Article> it = facade.iteratorFiltre();
            while(it.hasNext()){
                Article article = it.next();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("vignetteArticle.fxml"));
                // Injecter un contrôleur spécifique à cette vignette
                loader.setControllerFactory(ic -> new VignetteArticle(facade, article));
                // Charger le composant (la vignette)
                Node vignette = loader.load();
                vignette.setOnMouseClicked(e -> {
                    facade.setIndexArticleCourant(article);
                    gestionnaireDeVues.afficherVueDetails();
                });

                // Ajouter la vignette dans le TilePane
                tilePane.getChildren().add(vignette);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
