package vues;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import modele.Article;
import modele.Facade;

public class VignetteArticle implements Observateur {


    @FXML
    private Label labelNom;

    @FXML
    private Label labelPrix;

    @FXML
    private ImageView imageViewArticle;

    private Facade facade;
    private Article article;

    public VignetteArticle(Facade facade, Article article) {
        this.facade = facade;
        this.article = article;
    }

    @FXML
    public void initialize() {
        labelNom.setText(article.getNom());
        labelPrix.setText(String.format("%.2f €", article.getPrix()));
        try {
            // Chargement à partir du dossier resources/images
            Image image = new Image(getClass().getResourceAsStream("/images/" + article.getImgUrl()));
            imageViewArticle.setImage(image);
        } catch (Exception e) {
            System.err.println("Erreur chargement image : " + e.getMessage());
            // Image par défaut ou rien
            imageViewArticle.setImage(null);
        }

    }

    @FXML
    public void handleClick(MouseEvent mouseEvent) {
        // clic gauche affiche la vue détail
        if (mouseEvent.isPrimaryButtonDown()) {
            // déclencher la vue détaillée
        }
        // clic droit ? afficher menu contextuel (supprimer, etc)
    }

    @Override
    public void reagir() {

    }
}
