package modele;

import java.util.Iterator;

public class Facade extends SujetObserve implements Iterable<Article>{
    private CollectionArticle collectionArticle ;
    public Facade(){
        collectionArticle = new CollectionArticle() ;
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

    void setCategorieSelectionnee(String categorie){
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
    public void setModeEdition(){
        collectionArticle.setModeEdition();
        notifierObservateur();
    }

    public Iterator<Article> iteratorFiltre() {
        return collectionArticle.iterator();
    }

    @Override
    public Iterator<Article> iterator() {
        return collectionArticle.iterator() ;
    }
}
