package modele;

import java.util.Iterator;

public class Facade extends SujetObserve implements Iterable<Article>{
    private boolean modeAjout = false;

    private CollectionArticle collectionArticle ;
    public Facade(){
        collectionArticle = new CollectionArticle() ;

        ajouter("Risotto Poulet", 11.60, "/images/plat_2.png", true, "Riz crémeux et tendre poulet", Categorie.PLAT, "riz", "poulet");
        ajouter("Falafel", 7.40, "/images/falafel.png", true, "Boulettes végétariennes", Categorie.PLAT, "pois chiche", "végétarien");
        ajouter("Nuggets", 6.99, "/images/nuggets.png", true, "Croquants et dorés", Categorie.PLAT, "poulet", "frit");
        ajouter("Salade César", 7.50, "/images/saladcesar.png", true, "Salade romaine et poulet grillé", Categorie.PLAT, "salade", "poulet", "croutons");
        ajouter("Risotto Crevette", 8.30, "/images/risottocrevette.png", true, "Épicé et savoureux", Categorie.PLAT, "curry", "végétarien");
        ajouter("Veggie Wrap", 7.80, "/images/plat_1.png", true, "Wrap aux légumes grillés", Categorie.PLAT, "légumes", "wrap", "sans viande");
        ajouter("Burger Poulet", 6.99, "/images/burger_poulet.png", true, "Poulet croustillant et laitue", Categorie.PLAT, "poulet", "croustillant");
        ajouter("Cheeseburger", 7.99, "/images/cheeseburger.png", true, "Avec du bacon croustillant", Categorie.PLAT, "bacon", "salé");
        ajouter("Veggie vert", 9.99, "/images/plat_3.png", true, "Salade romaine, oeuf et sauce tomate", Categorie.PLAT, "bacon", "salé");

        ajouter("Petite Salade", 3.50, "/images/petitesalade.png", true, "Salade verte fraîche", Categorie.ACCOMPAGNEMENT, "salade", "légumes", "entrée");
        ajouter("Coleslaw", 2.80, "/images/coleslaw.png", true, "Chou blanc râpé, sauce crémeuse", Categorie.ACCOMPAGNEMENT, "chou", "entrée");
        ajouter("Salade Tomate", 3.90, "/images/saladetomate.png", true, "Tomates fraîches et basilic", Categorie.ACCOMPAGNEMENT, "tomate", "entrée");
        ajouter("Taboulé", 3.60, "/images/taboule.png", true, "Semoule, menthe, citron", Categorie.ACCOMPAGNEMENT, "semoule", "fraîcheur");
        ajouter("Frites", 2.50, "/images/frites.png", true, "Frites croustillantes", Categorie.ACCOMPAGNEMENT, "pommes de terre", "frit", "classique");

        ajouter("Tarte", 4.5, "/images/tarte.png", true, "Tarte croustillante aux fruits de saison", Categorie.DESSERT, "fruit", "pâtisserie", "frais");
        ajouter("Moelleux", 5.0, "/images/moelleux.png", true, "Gâteau fondant au chocolat noir", Categorie.DESSERT, "chocolat", "gâteau", "fondant");
        ajouter("Cake", 3.2, "/images/cake.png", true, "Petit cake moelleux au citron", Categorie.DESSERT, "citron", "léger", "moelleux");
        ajouter("Tiramisu", 5.2, "/images/tiramisu.png", true, "Tiramisu au café doux et mascarpone", Categorie.DESSERT, "café", "italien", "crémeux");
        ajouter("Crumble", 4.1, "/images/crumble.png", true, "Crumble aux pommes caramélisées", Categorie.DESSERT, "pomme", "croquant", "fruit");
        ajouter("Panna", 4.3, "/images/panna.png", true, "Panna cotta à la vanille et fruits rouges", Categorie.DESSERT, "fruit", "crémeux", "italien");
        ajouter("Éclair", 3.9, "/images/eclair.png", true, "Éclair au chocolat fondant", Categorie.DESSERT, "chocolat", "pâtisserie", "gâteau");

        ajouter("Virgin Mojito", 4.50, "/images/virginmojito.png", true, "Menthe, citron vert, eau pétillante", Categorie.BOISSON, "sans alcool", "menthe", "rafraîchissant");
        ajouter("Sunset Punch", 5.20, "/images/sunsetpunch.png", true, "Jus d'orange, grenadine, fruits", Categorie.BOISSON, "fruité", "sans alcool");
        ajouter("Berry Mix", 5.00, "/images/berrymix.png", true, "Fruits rouges mixés", Categorie.BOISSON, "baies", "cocktail", "frais");

        ajouter("Ridged", 2.00, "/images/ridged.png", true, "Boisson gazeuse classique", Categorie.BOISSON, "cola", "canette", "gazeux");
        ajouter("Peroni", 2.00, "/images/peroni.png", true, "Citron-lime pétillant", Categorie.BOISSON, "citron", "gazeux", "canette");
    }


    public void ajouter(String nom, double prix, String imgUrl, boolean disponible, String descript, Categorie categorie, String... motsCles){
        Article article = new Article(nom, prix, imgUrl, disponible, descript, categorie, motsCles) ;
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
        Article a = new Article("Plat", 2.99, "/images/default_image.jpg", true, "Un super plat ! ", Categorie.PLAT);
        collectionArticle.ajouter(a);
        setCategorieSelectionnee("Plat");
        setIndexArticleCourant(a);
    }
}
