package modele;

import java.util.*;

public class CollectionArticle implements Iterable<Article> {

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
        assert (index >= 0 && index < getArticlesFiltres().size()) : "l'index doit être entre 0 et nbArticlesFiltres - 1";
        this.indexArticleCourant = index;

    }

    public void setModeEdition(boolean b) {
        this.modeEdition = b;
    }

    public int getIndexArticleCourant() {
        return indexArticleCourant;
    }

    public Article getArticleCourant() {
        List<Article> articles = getArticlesFiltres();
        if (indexArticleCourant < 0 || indexArticleCourant >= articles.size()) {
            throw new IndexOutOfBoundsException("Index " + indexArticleCourant + " hors limites pour une liste de taille " + articles.size());
        }
        return articles.get(indexArticleCourant);
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

    public boolean estModeEdition() {
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
        return articles.size();
    }

    public int getnbArticlesFiltres() {
        return getArticlesFiltres().size();
    }

    public void setIndexArticleCourant(Article a) {
        indexArticleCourant = getArticlesFiltres().indexOf(a);
    }

    public void trierParNom() {
        Collections.sort(articles, new Comparator<Article>() {
            @Override
            public int compare(Article a1, Article a2) {
                return a1.getNom().compareToIgnoreCase(a2.getNom());
            }
        });

    }

    public void trierParPrix() {
        Collections.sort(articles, new Comparator<Article>() {
            @Override
            public int compare(Article a1, Article a2) {
                return Double.compare(a1.getPrix(), a2.getPrix());
            }
        });

    }
}
