package vues;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import modele.Article;
import modele.Categorie;
import modele.Facade;

import java.io.File;
import java.util.*;


public class VueDetails implements Observateur{

    private  Facade facade;
    private GestionnaireDeVues gestionnaireDeVues ;
    // === Mode consultation ===
    @FXML private Label nomLabel;
    @FXML private Text descriptionText;
    @FXML private Label prixLabel;
    @FXML private Label categorieLabel;
    @FXML private ListView<String> motsClesListView;

    @FXML private ImageView imageViewArticle;

    // === Mode édition ===
    @FXML private TextField nomTextField;
    @FXML private TextArea descriptionTextArea;
    @FXML private TextField prixTextField;
    @FXML private ComboBox<String> categorieComboBox;
    @FXML private ListView<String> motsClesEditListView;
    @FXML private TextField nouveauMotCleTextField;
    @FXML private ComboBox<String> motsClesComboBox;
    @FXML private Button ajouterMotCleButton;

    // === Boutons ===
    @FXML private Button precedentButton;
    @FXML private Button homeButton;
    @FXML private Button suivantButton;
    @FXML private Button modifierButton;
    @FXML private Button annulerButton;
    @FXML private Button sauvegarderButton;
    @FXML private Button changerImageButton ;

    // === Conteneurs de visibilité ===
    @FXML private VBox consultationInfoBox;
    @FXML private VBox editionInfoBox;
    @FXML private HBox categorieConsultationBox;
    @FXML private HBox categorieEditionBox;
    @FXML private HBox motsClesConsultationBox;
    @FXML private VBox motsClesEditionBox;
    @FXML private HBox navigationButtons;
    @FXML private HBox editionButtons;

    private File imageTemporaire = null;


    private ObservableList<String> tousLesMotsCles = FXCollections.observableArrayList();
   public VueDetails(Facade facade, GestionnaireDeVues gestionnaireDeVues){
       this.facade = facade;
       this.gestionnaireDeVues = gestionnaireDeVues ;
       facade.ajouterObservateur(this);
   }

    @FXML
    private void initialize() {
        chargerTousLesMotsCles();
        categorieComboBox.setItems(FXCollections.observableArrayList(
                "Plat", "Accompagnement", "Dessert", "Boisson"
        ));
        motsClesComboBox.setItems(tousLesMotsCles);
        configurerListViewEdition();
        if(facade.estModeEdition()){
            afficherModeEdition();
        }else{
            afficherModeConsultation();
        }

    }

    private void afficherDonnees() {
        if (facade.getArticleCourant() == null) return;
        Image image ;
        String url = facade.getArticleCourant().getImgUrl();
        if (url.startsWith("file:") || url.startsWith("http")) {
            image = new Image(url);
        } else {
            image = new Image(getClass().getResource(url).toExternalForm());
        }
        imageViewArticle.setImage(image);
        nomLabel.setText(facade.getArticleCourant().getNom());
        descriptionText.setText(facade.getArticleCourant().getDescription());
        prixLabel.setText(String.format("%.2f €", facade.getArticleCourant().getPrix()));
        categorieLabel.setText(facade.getArticleCourant().getCategorie().getLabel());
        motsClesListView.setItems(FXCollections.observableArrayList(facade.getArticleCourant().getMotsCles()));
    }

    private void remplirChampsEdition() {
        if (facade.getArticleCourant() == null) return;
        nomTextField.setText(facade.getArticleCourant().getNom());
        descriptionTextArea.setText(facade.getArticleCourant().getDescription());
        prixTextField.setText(String.valueOf(facade.getArticleCourant().getPrix()));
        categorieComboBox.setValue(facade.getArticleCourant().getCategorie().getLabel());
        motsClesEditListView.setItems(FXCollections.observableArrayList(facade.getArticleCourant().getMotsCles()));
    }

    private void afficherModeConsultation() {
        facade.setModeEdition(false);
        consultationInfoBox.setVisible(true);
        editionInfoBox.setVisible(false);
        categorieConsultationBox.setVisible(true);
        categorieEditionBox.setVisible(false);
        motsClesConsultationBox.setVisible(true);
        motsClesEditionBox.setVisible(false);
        navigationButtons.setVisible(true);
        editionButtons.setVisible(false);
        imageViewArticle.setVisible(true);
        changerImageButton.setVisible(false);
        afficherDonnees();
    }

    private void afficherModeEdition() {
        facade.setModeEdition(true);
        consultationInfoBox.setVisible(false);
        consultationInfoBox.setManaged(false);
        editionInfoBox.setVisible(true);
        categorieConsultationBox.setVisible(false);
        categorieConsultationBox.setManaged(false);
        categorieEditionBox.setVisible(true);
        motsClesConsultationBox.setVisible(false);
        motsClesConsultationBox.setManaged(false);
        motsClesEditionBox.setVisible(true);
        navigationButtons.setVisible(false);
        navigationButtons.setManaged(false);
        editionButtons.setVisible(true);
        imageViewArticle.setVisible(false);
        imageViewArticle.setManaged(false);
        changerImageButton.setVisible(true);
        if(!facade.estmodeAjout()){
            changerImageButton.setStyle(
                    "-fx-background-image: url('" + facade.getArticleCourant().getImgUrl() + "');" +
                            "-fx-background-size: cover;" +
                            "-fx-background-position: center;" +
                            "-fx-font-size: 36px;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-color: rgba(0,0,0,0.3);"
            );
        }

        remplirChampsEdition();
    }

    private void configurerListViewEdition() {
        motsClesEditListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(8);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    Label label = new Label(item);
                    Button btn = new Button("×");
                    btn.setOnAction(e -> {
                        getListView().getItems().remove(item);
                        if (!tousLesMotsCles.contains(item)) tousLesMotsCles.add(item);
                        motsClesComboBox.setItems(FXCollections.observableArrayList(tousLesMotsCles));

                    });
                    hbox.getChildren().addAll(label, btn);
                    setGraphic(hbox);
                }
            }
        });
    }

    public void ajouterMotCle() {
        String motCle = null;
        if (!nouveauMotCleTextField.getText().isBlank()) {
            motCle = nouveauMotCleTextField.getText().trim();
            nouveauMotCleTextField.clear();
        } else if (motsClesComboBox.getValue() != null) {
            motCle = motsClesComboBox.getValue();
            motsClesComboBox.setValue(null);
        }

        if (motCle == null || motCle.isBlank()) return;

        if (!motsClesEditListView.getItems().contains(motCle)) {
            motsClesEditListView.getItems().add(motCle);
            motsClesComboBox.getItems().remove(motCle);
        } else {
            showAlert("Info", "Mot-clé déjà présent.");
        }
    }

    @FXML
    private void sauvegarder() {
        try {
            if (nomTextField.getText().isBlank()) {
                showAlert("Erreur", "Le nom est requis.");
                return;
            }

            double prix = Double.parseDouble(prixTextField.getText());
            facade.getArticleCourant().setNom(nomTextField.getText().trim());
            facade.getArticleCourant().setDescription(descriptionTextArea.getText().trim());
            facade.getArticleCourant().setPrix(prix);
            facade.getArticleCourant().setCategorie(Categorie.getCategorie(categorieComboBox.getValue()));
            if(facade.estmodeAjout()){
                facade.setCategorieSelectionnee(Categorie.getCategorie(categorieComboBox.getValue()).getLabel());
            }
            facade.getArticleCourant().setMotsCles(new ArrayList<>(motsClesEditListView.getItems()));

            if (imageTemporaire != null) {
                facade.getArticleCourant().setImgUrl(imageTemporaire.toURI().toString());
                imageTemporaire = null;
            }
            if(facade.estmodeAjout()){
                gestionnaireDeVues.afficherVueGlobale();
                facade.setModeAjout(false);
            }else{
                afficherModeConsultation();
            }

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Prix invalide.");
        }
    }


    @FXML
    private void annuler() {
        if (facade.estmodeAjout()) {
            // Si on était en train d'ajouter, on retourne à la vue globale sans sauvegarder
            facade.supprimer(facade.getArticleCourant());
            gestionnaireDeVues.afficherVueGlobale();
            facade.setModeAjout(false);
        } else {
            // Sinon on retourne en consultation
            afficherModeConsultation();
        }
    }


    @FXML
    private void basculerModeEdition() {

        if (facade.estModeEdition()) afficherModeConsultation();
        else afficherModeEdition();
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void chargerTousLesMotsCles() {
        Set<String> motsClesUniques = new HashSet<>();
        for (Article a : facade) {
            motsClesUniques.addAll(a.getMotsCles());
        }
        List<String> motsClesTries = new ArrayList<>(motsClesUniques);
        Collections.sort(motsClesTries);
        tousLesMotsCles.setAll(motsClesTries);
    }

    @FXML
    private void precedent() {
       facade.precedent();
       afficherModeConsultation();
   }
    @FXML private void home() {
       gestionnaireDeVues.afficherVueGlobale();
    }
    @FXML private void suivant() {
        facade.suivant();
        afficherModeConsultation();}

    @Override
    public void reagir() {
        if (facade.estModeEdition()) {
            afficherModeEdition(); // recharge les champs d'édition si nécessaire
        } else {
            afficherModeConsultation(); // recharge l'affichage en mode consultation
        }
    }


    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (file != null) {
            imageTemporaire = file; // stock l'image pour l'utiliser dans sauvegarder

            // Prévisualisation de l'image dans le bouton uniquement
            changerImageButton.setStyle(
                    "-fx-background-image: url('" + file.toURI().toString() + "');" +
                            "-fx-background-size: cover;" +
                            "-fx-background-position: center;" +
                            "-fx-font-size: 36px;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-color: rgba(0,0,0,0.3);"
            );
        }
    }


}
