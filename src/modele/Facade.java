package modele;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class Facade extends SujetObserve implements Iterable<Article>{
    private boolean modeAjout = false;
    private Gson gson;
    private CollectionArticle collectionArticle ;
    public Facade(){
        collectionArticle = new CollectionArticle() ;

        // Initialiser Gson avec une configuration personnalisée
        gson = new GsonBuilder()
                .setPrettyPrinting() // Pour un JSON formaté
                .serializeNulls() // Inclure les valeurs null
                .create();


    }


    public void ajouter(String nom, double prix, String imgUrl, String descript, Categorie categorie, String... motsCles){
        Article article = new Article(nom, prix, imgUrl, descript, categorie, motsCles) ;
        collectionArticle.ajouter(article);
        notifierObservateur() ;
    }

    public void supprimer(Article article){
        collectionArticle.supprimer(article);
    }

    public void setCategorieSelectionnee(String categorie){
        collectionArticle.setCategorieSelectionnee(Categorie.getCategorie(categorie));
        notifierObservateur();
    }
    public Article getArticleCourant(){
        return collectionArticle.getArticleCourant() ;
    }
    public void precedent(){
        int indexCourant  = collectionArticle.getIndexArticleCourant();
        if(indexCourant > 0 ){
            collectionArticle.setIndexArticleCourant(indexCourant - 1);
            notifierObservateur();
        }
    }

    public void suivant(){
        int indexCourant  = collectionArticle.getIndexArticleCourant();
        if(indexCourant < collectionArticle.getnbArticlesFiltres() - 1 ){
            collectionArticle.setIndexArticleCourant(indexCourant + 1);
            notifierObservateur();
        }
    }
    public void setModeEdition(boolean b){
        collectionArticle.setModeEdition(b);
    }

    public Iterator<Article> iteratorFiltre() {
        return collectionArticle.iteratorFiltre();
    }

    @Override
    public Iterator<Article> iterator() {
        return collectionArticle.iterator() ;
    }

    public boolean estModeEdition() {
        return collectionArticle.estModeEdition() ;
    }

    public void setIndexArticleCourant(Article a){
        collectionArticle.setIndexArticleCourant(a);
    }

    public boolean estmodeAjout() {
        return modeAjout;
    }

    public void setModeAjout(boolean b){
        this.modeAjout = b ;
    }

    public void ajouterNouvel() {
        Article a = new Article("Plat", 2.99, "/images/default_image.jpg", "Un super plat ! ", Categorie.PLAT);
        collectionArticle.ajouter(a);
        setCategorieSelectionnee("Plat");
        setIndexArticleCourant(a);
    }

    public void sauvegarderJSON(String cheminFichier) throws IOException {
        MenuData menuData = new MenuData();

        // Convertir tous les articles en ArticleData
        for (Article article : collectionArticle) {
            menuData.getArticles().add(new ArticleData(article));
        }

        // Écrire dans le fichier avec Gson
        try (FileWriter fileWriter = new FileWriter(cheminFichier)) {
            gson.toJson(menuData, fileWriter);
        }
    }

    public void chargerJSON(String cheminFichier) throws IOException, JsonSyntaxException {
        // Vérifier que le fichier existe
        if (!Files.exists(Paths.get(cheminFichier))) {
            throw new FileNotFoundException("Le fichier " + cheminFichier + " n'existe pas.");
        }
        // Lire et parser le fichier JSON avec Gson
        try (FileReader fileReader = new FileReader(cheminFichier)) {
            MenuData menuData = gson.fromJson(fileReader, MenuData.class);
            // Vider la collection actuelle
            collectionArticle = new CollectionArticle();
            // Charger les articles
            if (menuData != null && menuData.getArticles() != null) {
                for (ArticleData articleData : menuData.getArticles()) {
                    Article article = new Article(
                            articleData.getNom(),
                            articleData.getPrix(),
                            articleData.getImgUrl(),
                            articleData.getDescription(),
                            Categorie.valueOf(articleData.getCategorie()),
                            articleData.getMotsCles()
                    );
                    collectionArticle.ajouter(article);
                }
            }

            notifierObservateur();
        }
    }


    public void trierArticlesParNom() {
        collectionArticle.trierParNom();
        notifierObservateur(); // Notifier les vues pour qu'elles se mettent à jour
    }

    public void trierArticlesParPrix() {
        collectionArticle.trierParPrix();
        notifierObservateur(); // Notifier les vues pour qu'elles se mettent à jour
    }

}
