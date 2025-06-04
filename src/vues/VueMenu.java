package vues;

import com.google.gson.JsonSyntaxException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Categorie;
import modele.Facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class VueMenu implements Observateur{
    private Facade facade;
    private GestionnaireDeVues gestionnaireDeVues;

    public VueMenu(Facade facade, GestionnaireDeVues gestionnaireDeVues){
        this.facade = facade ;
        this.gestionnaireDeVues = gestionnaireDeVues ;
        facade.ajouterObservateur(this);
    }
    private void afficherAlerteInfo(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Afficher l'alerte sans bloquer
        alert.show();

        // Créer un Timeline pour fermer l'alerte après 3 secondes
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(3),
                e -> alert.close()
        ));
        timeline.play();
    }
    private void afficherAlerteErreur(String titre, String entete, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(entete);
        alert.setContentText(message);

        // Afficher l'alerte sans bloquer
        alert.show();

        // Créer un Timeline pour fermer l'alerte après 3 secondes
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(3),
                e -> alert.close()
        ));
        timeline.play();
    }

    private FileChooser configurerFileChooserJson(String titre) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(titre);

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Fichiers JSON (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        return fileChooser;
    }

    public void importerJson(ActionEvent actionEvent) {
        FileChooser fileChooser = configurerFileChooserJson("Importer un fichier JSON");

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                facade.chargerJSON(selectedFile.getAbsolutePath());
                afficherAlerteInfo("Import réussi",
                        "Les articles ont été importés avec succès depuis : " + selectedFile.getName());

            } catch (FileNotFoundException e) {
                afficherAlerteErreur("Erreur d'import", "Fichier non trouvé",
                        "Le fichier sélectionné n'existe pas.");

            } catch (JsonSyntaxException e) {
                afficherAlerteErreur("Erreur d'import", "Format JSON invalide",
                        "Le fichier sélectionné ne contient pas un JSON valide.\nErreur : " + e.getMessage());

            } catch (IOException e) {
                afficherAlerteErreur("Erreur d'import", "Erreur de lecture",
                        "Impossible de lire le fichier.\nErreur : " + e.getMessage());
            }
        }
    }

    public void sauvegarderJson(ActionEvent actionEvent) {
        FileChooser fileChooser = configurerFileChooserJson("Sauvegarder en JSON");
        fileChooser.setInitialFileName("menu_export.json");

        File selectedFile = fileChooser.showSaveDialog(null); // ← Changé ici

        if (selectedFile != null) {
            try {
                String filePath = selectedFile.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".json")) {
                    filePath += ".json";
                }

                facade.sauvegarderJSON(filePath);
                afficherAlerteInfo("Sauvegarde réussie",
                        "Le menu a été sauvegardé avec succès dans : " + new File(filePath).getName());

            } catch (IOException e) {
                afficherAlerteErreur("Erreur de sauvegarde", "Impossible de sauvegarder",
                        "Une erreur s'est produite lors de la sauvegarde.\nErreur : " + e.getMessage());
            }
        }
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
        try {
            facade.trierArticlesParNom();
            afficherAlerteInfo("Tri effectué", "Les articles ont été triés par nom.");


        } catch (Exception e) {
            afficherAlerteErreur("Erreur de tri", "Impossible de trier par nom",
                    "Une erreur s'est produite lors du tri.\nErreur : " + e.getMessage());
        }
    }

    public void trierParPrix(ActionEvent actionEvent) {
        try {
            facade.trierArticlesParPrix();
            afficherAlerteInfo("Tri effectué", "Les articles ont été triés par prix.");

        } catch (Exception e) {
            afficherAlerteErreur("Erreur de tri", "Impossible de trier par prix",
                    "Une erreur s'est produite lors du tri.\nErreur : " + e.getMessage());
        }
    }
    @Override
    public void reagir() {

    }
}
