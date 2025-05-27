package modele;

public enum Categorie {
    PLAT("Plat"),
    ACCOMPAGNEMENT("Accompagnement"),
    DESSERT("Dessert"),
    BOISSON("Boisson");

    private final String label;

    private Categorie(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Categorie getCategorie(String label) {
        for (Categorie c : Categorie.values()) {
            if (c.getLabel().equals(label)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Aucune catégorie ne correspond au label : " + label);
    }


}
