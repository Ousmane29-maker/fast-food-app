import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modele.Facade;
import vues.VueCategorie;
import vues.VueGlobale;
import vues.VueMenu;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Facade facade = new Facade();

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/vues/VueMenu.fxml"));
        menuLoader.setControllerFactory(ic -> new VueMenu(facade));
        Node vueMenu = menuLoader.load();

        FXMLLoader categorieLoader = new FXMLLoader(getClass().getResource("/vues/VueCategorie.fxml"));
        categorieLoader.setControllerFactory(ic -> new VueCategorie(facade));
        Node vueCategorie = categorieLoader.load();

        FXMLLoader globaleLoader = new FXMLLoader(getClass().getResource("/vues/VueGlobale.fxml"));
        globaleLoader.setControllerFactory(ic -> new VueGlobale(facade));
        Node vueGlobale = globaleLoader.load();

        // Créer un HBox pour contenir les vues catégorie et globale
        HBox contenuPrincipal = new HBox(vueCategorie, vueGlobale);
        contenuPrincipal.setAlignment(Pos.TOP_LEFT);
        contenuPrincipal.setSpacing(20); // espacement horizontal entre les deux
        contenuPrincipal.setPadding(new Insets(10)); // un peu marges

        // Placer le menu en haut et le contenu au centre
        BorderPane root = new BorderPane();
        root.setTop(vueMenu);
        root.setCenter(contenuPrincipal);


        Scene scene = new Scene(root, 750, 600);
        primaryStage.setTitle("Fast food");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
