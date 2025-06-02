package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Article {

    private String nom;
    private double prix;
    private String imgUrl;
    private boolean disponible;
    private String descript;
    private Categorie categorie;
    private List<String> motsCles;

    public Article(String nom, double prix, String imgUrl, boolean disponible, String descript, Categorie categorie, String... motsCles) {
        this.nom = nom;
        this.prix = prix;
        this.imgUrl = imgUrl;
        this.disponible = disponible;
        this.descript = descript;
        this.categorie = categorie;
        this.motsCles = new ArrayList<>();
        ajouterMotsCles(motsCles);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.descript = description;
    }

    public void setDisponibilite(boolean b) {
        this.disponible = b;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void ajouterMotsCles(String... motsCles) {
        this.motsCles.addAll(Arrays.asList(motsCles));
    }

    public void supprimerMotCle(String cle) {
        this.motsCles.remove(cle);
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public boolean estDisponible() {
        return disponible;
    }

    public String getDescription() {
        return descript;
    }

    public List<String> getMotsCles() {
        return motsCles;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setMotsCles(ArrayList<String> mots_cles) {
        motsCles = mots_cles ;
    }
}
