package modele;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour structurer les données JSON du menu
 */
public class MenuData {
    private String version;
    private String dateExport;
    private List<ArticleData> articles;

    public MenuData() {
        this.version = "1.0";
        this.dateExport = LocalDateTime.now().toString();
        this.articles = new ArrayList<>();
    }

    // Getters et setters
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDateExport() {
        return dateExport;
    }

    public void setDateExport(String dateExport) {
        this.dateExport = dateExport;
    }

    public List<ArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleData> articles) {
        this.articles = articles;
    }
}