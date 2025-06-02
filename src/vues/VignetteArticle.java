package vues;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        Image image;
        try {
            String url = article.getImgUrl();
            if (url.startsWith("file:") || url.startsWith("http")) {
                image = new Image(url);
            } else {
                image = new Image(getClass().getResource(url).toExternalForm());
            }
            imageViewArticle.setImage(image);
        } catch (Exception e) {
            System.err.println("Erreur image : " + article.getImgUrl());
            e.printStackTrace();
            imageViewArticle.setImage(new Image(getClass().getResource("/images/default_image.jpg").toExternalForm()));
        }


    }

    @Override
    public void reagir() {

    }
}
