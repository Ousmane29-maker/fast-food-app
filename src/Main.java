import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Facade;
import vues.*;

public class Main extends Application {

    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Facade facade = new Facade();
//
//        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/vues/VueMenu.fxml"));
//        menuLoader.setControllerFactory(ic -> new VueMenu(facade));
//        Node vueMenu = menuLoader.load();
//
//        FXMLLoader categorieLoader = new FXMLLoader(getClass().getResource("/vues/VueCategorie.fxml"));
//        categorieLoader.setControllerFactory(ic -> new VueCategorie(facade));
//        Node vueCategorie = categorieLoader.load();
//
//        FXMLLoader globaleLoader = new FXMLLoader(getClass().getResource("/vues/VueGlobale.fxml"));
//        globaleLoader.setControllerFactory(ic -> new VueGlobale(facade));
//        Node vueGlobale = globaleLoader.load();
//
//        // Créer un HBox pour contenir les vues catégorie et globale
//        HBox contenuPrincipal = new HBox(vueCategorie, vueGlobale);
//        contenuPrincipal.setAlignment(Pos.TOP_LEFT);
//        contenuPrincipal.setSpacing(20); // espacement horizontal entre les deux
//        contenuPrincipal.setPadding(new Insets(10)); // un peu marges
//
//        // Placer le menu en haut et le contenu au centre
//        BorderPane root = new BorderPane();
//        root.setTop(vueMenu);
//        root.setCenter(contenuPrincipal);
//
//
//        Scene scene = new Scene(root, 750, 600);
//        primaryStage.setTitle("Fast food");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    public void start(Stage primaryStage) throws Exception {
        Facade facade = new Facade();
        BorderPane root = new BorderPane();
        GestionnaireDeVues gestionnaire = new GestionnaireDeVues(root, facade);

        // Vue Menu
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/vues/VueMenu.fxml"));
        VueMenu MenuControl = new VueMenu(facade, gestionnaire);
        menuLoader.setController(MenuControl);
        Node vueMenu = menuLoader.load();

        gestionnaire.setVueMenu(vueMenu);

        // Vue Categorie
        FXMLLoader categorieLoader = new FXMLLoader(getClass().getResource("/vues/VueCategorie.fxml"));
        categorieLoader.setControllerFactory(ic -> new VueCategorie(facade));
        Node vueCategorie = categorieLoader.load();

        // Vue Globale
        FXMLLoader globaleLoader = new FXMLLoader(getClass().getResource("/vues/VueGlobale.fxml"));
        VueGlobale globaleCtrl = new VueGlobale(facade, gestionnaire); //
        globaleLoader.setController(globaleCtrl);
        Node vueGlobale = globaleLoader.load();



        // Enregistrement dans le gestionnaire
        gestionnaire.setVueGlobale(vueGlobale, vueCategorie);

        // Lancement
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Fast food");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
