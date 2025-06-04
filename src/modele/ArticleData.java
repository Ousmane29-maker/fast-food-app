package modele;

/**
 * Classe pour représenter un article dans le JSON
 */
public class ArticleData {
    private String nom;
    private double prix;
    private String imgUrl;
    private String description;
    private String categorie;
    private String[] motsCles;

    public ArticleData() {}

    public ArticleData(Article article) {
        this.nom = article.getNom();
        this.prix = article.getPrix();
        this.imgUrl = article.getImgUrl();
        this.description = article.getDescription();
        this.categorie = article.getCategorie().toString();
        this.motsCles = article.getMotsCles().toArray(new String[0]);
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String[] getMotsCles() {
        return motsCles;
    }

    public void setMotsCles(String[] motsCles) {
        this.motsCles = motsCles;
    }
}