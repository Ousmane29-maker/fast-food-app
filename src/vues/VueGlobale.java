package vues;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.TilePane;
import modele.Article;
import modele.Facade;

import java.util.Iterator;
import java.util.Optional;

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
                    if (e.getButton() == MouseButton.PRIMARY) { // Clic gauche
                        facade.setIndexArticleCourant(article);
                        gestionnaireDeVues.afficherVueDetails();
                    }

                    if (e.getButton() == MouseButton.SECONDARY) { // Clic droit
                        // Création du menu contextuel
                        ContextMenu contextMenu = new ContextMenu();

                        // Option "Supprimer"
                        MenuItem supprimerItem = new MenuItem("Supprimer");
                        supprimerItem.setOnAction(event -> {
                            // Afficher une confirmation avant suppression
                            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                            confirmation.setTitle("Confirmation");
                            confirmation.setHeaderText("Supprimer l'article");
                            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer cet article ?");

                            Optional<ButtonType> result = confirmation.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                facade.supprimer(article);
                                reagir(); // Rafraîchir la vue
                            }
                        });

                        // Ajouter l'item au menu
                        contextMenu.getItems().add(supprimerItem);

                        // Afficher le menu à la position du clic
                        contextMenu.show(vignette, e.getScreenX(), e.getScreenY());
                    }
                });


                // Ajouter la vignette dans le TilePane
                tilePane.getChildren().add(vignette);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
