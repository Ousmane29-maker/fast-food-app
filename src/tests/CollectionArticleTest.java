package tests;

import modele.Article;
import modele.Categorie;
import modele.CollectionArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionArticleTest {

    private CollectionArticle collection;
    private Article a1, a2, a3;

    @BeforeEach
    void setUp() {
        collection = new CollectionArticle();

        a1 = new Article("Pizza", 10.5, "pizza.png", "Délicieuse pizza", Categorie.PLAT, "pizza", "plats", "pates");
        a1.setCategorie(Categorie.PLAT);

        a2 = new Article("Coca", 2.5, "coca.png", "Boisson gazeuse", Categorie.BOISSON, "boisson", "sucre");
        a2.setCategorie(Categorie.BOISSON);

        a3 = new Article("Tiramisu", 4.0, "tiramisu.png", "Dessert italien", Categorie.DESSERT,"dessert");
        a3.setCategorie(Categorie.DESSERT);

        collection.ajouter(a1);
        collection.ajouter(a2);
        collection.ajouter(a3);
    }

    @Test
    void testAjout() {
        assertEquals(3, collection.getnbArticles()); // test simple
        assertTrue(collection.iterator().hasNext());
    }

    @Test
    void testSuppression() {
        collection.supprimer(a1);
        assertEquals(2, collection.getnbArticles());
    }

    @Test
    void testFiltrageCategorie() {
        collection.setCategorieSelectionnee(Categorie.BOISSON);
        assertEquals(1, collection.getArticlesFiltres().size());
        assertEquals("Coca", collection.getArticleCourant().getNom());
    }

    @Test
    void testIndexValide() {
        collection.setCategorieSelectionnee(Categorie.PLAT);
        assertThrows(AssertionError.class, () -> collection.setIndexArticleCourant(2));
        collection.setIndexArticleCourant(0) ;
        assertEquals("Pizza", collection.getArticleCourant().getNom());
    }

    @Test
    void testModeEdition() {
        assertFalse(collection.estModeEdition());
        collection.setModeEdition(true);
        assertTrue(collection.estModeEdition());
    }

    @Test
    void testIteratorFiltre() {
        collection.setCategorieSelectionnee(Categorie.DESSERT);
        assertTrue(collection.iteratorFiltre().hasNext());
        assertEquals("Tiramisu", collection.iteratorFiltre().next().getNom());
    }
}

