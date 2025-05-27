package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionArticle implements Iterable<Article>{

    private boolean modeEdition;
    private int indexArticleCourant;
    private List<Article> articles;
    private Categorie categorieSelectionnee;

    public CollectionArticle() {
        this.articles = new ArrayList<>();
        this.indexArticleCourant = 0;
        this.modeEdition = false;
        this.categorieSelectionnee = Categorie.PLAT; // Par défaut
    }

    public void ajouter(Article article) {
        articles.add(article);
    }

    public void supprimer(Article article) {
        articles.remove(article);
    }

    public void setCategorieSelectionnee(Categorie categorie) {
        this.categorieSelectionnee = categorie;
        this.indexArticleCourant = 0;
    }

    public void setIndexArticleCourant(int index) {
        assert(index >= 0 && index < getArticlesFiltres().size()) : "l'index doit être entre 0 et nbArticlesFiltres - 1";
        this.indexArticleCourant = index;

    }

    public void setModeEdition() {
        this.modeEdition = !this.modeEdition;
    }

    public int getIndexArticleCourant() {
        return indexArticleCourant;
    }

    public Article getArticleCourant() {
        assert (indexArticleCourant >= 0 && indexArticleCourant < getArticlesFiltres().size()) : "l'index doit être entre 0 et nbArticlesFiltres - 1" ;
        return getArticlesFiltres().get(indexArticleCourant);

    }

    public List<Article> getArticlesFiltres() {
        List<Article> filtres = new ArrayList<>();
        for (Article a : articles) {
            if (a.getCategorie() == categorieSelectionnee) {
                filtres.add(a);
            }
        }
        return filtres;
    }

    public boolean isModeEdition() {
        return modeEdition;
    }

    public Categorie getCategorieSelectionnee() {
        return categorieSelectionnee;
    }

    @Override
    public Iterator<Article> iterator() {
        return articles.iterator();
    }
    public Iterator<Article> iteratorFiltre() {
        return getArticlesFiltres().iterator();
    }

    public int getnbArticles() {
        return articles.size() ;
    }

    public int getnbArticlesFiltres() {
        return getArticlesFiltres().size() ;
    }
}
