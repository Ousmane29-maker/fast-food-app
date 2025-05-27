package modele;

import java.util.Iterator;

public class Facade extends SujetObserve implements Iterable<Article>{
    private CollectionArticle collectionArticle ;
    public Facade(){
        collectionArticle = new CollectionArticle() ;
        //testssss
//        ajouter("Burger Classique", 5.99, "../ressources/images/burger.png", true, "Un burger traditionnel savoureux", Categorie.PLAT, "classique", "traditionnel");
//        ajouter("Burger Fromage", 6.49, "../ressources/images/burger.png", true, "Avec un fromage fondant", Categorie.PLAT, "fromage", "cheddar");
//        ajouter("Burger Poulet", 6.99, "../ressources/images/burger.png", true, "Poulet croustillant et laitue", Categorie.PLAT, "poulet", "croustillant");
//        ajouter("Burger Vegan", 7.49, "../ressources/images/burger.png", true, "Sans viande, riche en goût", Categorie.PLAT, "vegan", "sans viande");
//        ajouter("Burger Double", 8.49, "../ressources/images/burger.png", true, "Deux steaks, deux fois plus de plaisir", Categorie.PLAT, "double", "viande");
//        ajouter("Burger BBQ", 7.29, "../ressources/images/burger.png", true, "Sauce barbecue fumée", Categorie.PLAT, "bbq", "fumé");
//        ajouter("Burger Bacon", 7.99, "../ressources/images/burger.png", true, "Avec du bacon croustillant", Categorie.PLAT, "bacon", "salé");
//        ajouter("Burger Spicy", 6.89, "../ressources/images/burger.png", true, "Épicé comme il faut", Categorie.PLAT, "épicé", "piquant");
//        ajouter("Burger Champignon", 6.79, "../ressources/images/burger.png", true, "Champignons grillés", Categorie.PLAT, "champignon", "végétarien");
//        ajouter("Burger Fish", 7.59, "../ressources/images/burger.png", true, "Filet de poisson pané", Categorie.PLAT, "poisson", "mer");
//        ajouter("Burger Bleu", 7.99, "../ressources/images/burger.png", true, "Avec du fromage bleu", Categorie.PLAT, "bleu", "fromage");
//        ajouter("Burger Oeuf", 6.49, "../ressources/images/burger.png", true, "Ajout d’un œuf frais", Categorie.PLAT, "œuf", "brunch");
//        ajouter("Burger Mexicain", 7.29, "../ressources/images/burger.png", true, "Saveurs tex-mex", Categorie.DESSERT, "mexicain", "tex-mex");
//        ajouter("Burger Italien", 7.49, "../ressources/images/burger.png", true, "Tomate, mozzarella, pesto", Categorie.BOISSON, "italien", "basilic");
//        ajouter("Burger Deluxe", 9.49, "../ressources/images/burger.png", true, "Version premium du burger", Categorie.BOISSON, "deluxe", "premium");

    }


    public void ajouter(String nom, double prix, String imgUrl, boolean disponible, String descript, Categorie categorie, String... motsCles){
        Article article = new Article(nom, prix, imgUrl, disponible, descript, categorie, motsCles) ;
        collectionArticle.ajouter(article);
        notifierObservateur() ;
    }

    public void supprimer(Article article){
        collectionArticle.supprimer(article);
        notifierObservateur();
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
        notifierObservateur();
    }

    public Iterator<Article> iteratorFiltre() {
        return collectionArticle.iteratorFiltre();
    }

    @Override
    public Iterator<Article> iterator() {
        return collectionArticle.iterator() ;
    }
}
