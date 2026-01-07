# 🍔 Application de Gestion Fast Food

Application desktop JavaFX avec architecture **MVC + Observer** pour la gestion complète d'un menu de fast food.

---

## 📸 Aperçu de l'Application

### Vue Globale Plats
![Vue Globale](../../../../../readme_project/vue_globale1.png)
*Affichage en grille de tous les Plats*

### Vue Globale Desserts
![Vue Globale](../../../../../readme_project/vue_globale2.png)
*Affichage en grille de tous les Dessert*

### Vue Globale Boissons
![Vue Globale](../../../../../readme_project/vue_globale3.png)
*Affichage en grille de tous les Boissons*

### Mode Édition
![Mode Édition](../../../../../readme_project/mode_edition.png)
*Interface de modification avec champs éditables et gestion des images*

---

## 📋 Description

Application de gestion de menu développée en Java avec JavaFX, implémentant une architecture **Model-View-Controller** enrichie du pattern **Observer** pour une mise à jour réactive de l'interface. Le projet permet la gestion complète d'articles de restauration rapide avec catégorisation, recherche par mots-clés et persistence JSON.

---

## 🎯 Fonctionnalités Principales

### 📦 Gestion des Articles
- **CRUD complet** : Création, Lecture, Modification, Suppression
- **Catégorisation** : Plat, Accompagnement, Dessert, Boisson
- **Attributs détaillés** : Nom, Prix, Image, Description, Disponibilité
- **Mots-clés** : Système de tags pour recherche et filtrage avancés

### 🔍 Navigation et Filtrage
- **Filtrage par catégorie** : Vue dédiée pour chaque type d'article
- **Recherche par mots-clés** : Tags multiples pour affiner les résultats
- **Navigation séquentielle** : Boutons précédent/suivant
- **Vue globale** : Affichage en grille avec vignettes

### 💾 Persistence des Données
- **Export JSON** : Sauvegarde complète du catalogue
- **Import JSON** : Chargement de données existantes
- **Format structuré** : Données facilement éditables

### 🎨 Interface Utilisateur
- **Double mode** : Consultation / Édition
- **Vue détaillée** : Fiche complète de chaque article
- **Vignettes interactives** : Aperçu visuel avec actions rapides
- **Gestion d'images** : Sélection et affichage d'illustrations

### 📊 Fonctionnalités Avancées
- **Tri intelligent** : Par nom (alphabétique) ou par prix
- **Mode ajout** : Création facilitée de nouveaux articles
- **Notifications réactives** : Mise à jour automatique de toutes les vues

---

## 🏗️ Architecture Technique

### Pattern MVC + Observer

```
┌─────────────────────────────────────────────┐
│              MODÈLE (Model)                 │
│  ┌────────────────────────────────────┐    │
│  │         Facade                      │    │
│  │  - CollectionArticle                │    │
│  │  - Gestion métier                   │    │
│  └────────────────────────────────────┘    │
│         ▲                                    │
│         │ Observer Pattern                  │
│         │                                    │
├─────────┼────────────────────────────────────┤
│         │                                    │
│  ┌──────▼──────────────────────────────┐    │
│  │      SujetObservé                   │    │
│  │  - notifierObservateurs()           │    │
│  └─────────────────────────────────────┘    │
│         │                                    │
│         │ notifie                           │
│         ▼                                    │
├─────────────────────────────────────────────┤
│              VUES (Views)                   │
│  ┌─────────────────────────────────────┐   │
│  │  Observateur (interface)            │   │
│  │  - reagir()                         │   │
│  └─────────────────────────────────────┘   │
│         ▲                                    │
│         │ implémente                        │
│         │                                    │
│  ┌──────┴──────┬─────────┬─────────┐       │
│  │             │         │         │       │
│  VueGlobale  VueDetaillee VueMenu  ...     │
└─────────────────────────────────────────────┘
```

### Composants Principaux

#### 📦 Modèle (Package `modele`)
- **Article** : Entité représentant un produit
- **Categorie** : Énumération (Plat, Accompagnement, Dessert, Boisson)
- **CollectionArticle** : Gestion de la collection et filtres
- **Facade** : Point d'entrée unifié, implémente SujetObserve
- **SujetObserve** : Gestion des observateurs (pattern Observer)

#### 🎨 Vues (Package `vues`)
- **VueGlobale** : Affichage en grille de tous les articles
- **VueDetaillee** : Fiche détaillée avec mode édition
- **VueCategorie** : Filtrage par catégorie
- **VignetteArticle** : Composant réutilisable pour l'affichage
- **VueMenu** : Barre de menu avec actions globales
- **GestionnaireDeVues** : Navigation entre les vues

---

## 🛠️ Stack Technique

| Composant | Technologie |
|-----------|-------------|
| **Langage** | Java |
| **Interface graphique** | JavaFX |
| **Architecture** | MVC + Observer Pattern |
| **Persistence** | JSON (import/export) |
| **IDE** | IntelliJ IDEA |

---

## 📂 Structure du Projet

```
fast-food-app/
├── src/
│   ├── modele/
│   │   ├── Article.java
│   │   ├── Categorie.java
│   │   ├── CollectionArticle.java
│   │   ├── Facade.java
│   │   └── SujetObserve.java
│   ├── vues/
│   │   ├── VueGlobale.java     # le controlleur 
│   │   ├── VueGlobale.fxml     # la vue associé
│   │   ├── VueCategorie.java
│   │   ├── VueCategorie.fxml
│   │   ├── VueMenu.java
│   │   ├── VueMenu.fxml
│   │   ├── Observateur.java   #Interface
│   │   └── GestionnaireDeVues.java
│   └── resources/
│       ├── css/
        ├── json/ # Fichier de données
│       └── images/ 
└── README.md
```

---

## 🚀 Installation & Lancement

### Prérequis
- **Java JDK 17** ou supérieur
- **JavaFX SDK** 
- **IntelliJ IDEA** (recommandé) ou tout IDE Java

### Installation

```bash
# Clone le projet
git clone https://github.com/Ousmane29-maker/fast-food-app.git
cd fast-food-app

```

### Lancement

#### Avec IntelliJ IDEA
1. Ouvrir le projet
2. Configurer le SDK Java
3. Run → Run 'Main'

#### En ligne de commande
```bash
# Avec Java
java --module-path /chemin/vers/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar fast-food-app.jar
```

---

## 💡 Utilisation

### Mode Consultation
1. **Navigation** : Utilisez les flèches ou cliquez sur les vignettes
2. **Filtrage** : Sélectionnez une catégorie dans le menu latéral
3. **Détails** : Cliquez sur un article pour voir sa fiche complète

### Mode Édition
1. Cliquez sur le bouton **"Éditer"** dans la vue détaillée
2. Modifiez les champs (nom, prix, description, etc.)
3. Ajoutez/supprimez des mots-clés
4. Cliquez sur **"Sauvegarder"** ou **"Annuler"**

### Ajout d'Article
1. Menu → Edition → **"Nouvel Article"**
2. Remplissez tous les champs
3. Sélectionnez une image
4. Ajoutez des mots-clés
5. Cliquez sur **"Sauvegarder"**

### Import/Export JSON
- **Exporter** : Menu → Sauvegarder → Choisir l'emplacement
- **Importer** : Menu → Importer → Sélectionner le fichier JSON

---

## 📊 Format de Données JSON

```json
{
  "version": "1.0",
  "dateExport": "2025-06-04T22:39:42.944249",
  "articles": [
    {
      "nom": "Risotto Poulet",
      "prix": 11.6,
      "imgUrl": "/images/plat_2.png",
      "description": "Riz crémeux et tendre poulet",
      "categorie": "PLAT",
      "motsCles": [
        "riz",
        "poulet"
      ]
    },
    {
      "nom": "Falafel",
      "prix": 7.4,
      "imgUrl": "/images/falafel.png",
      "description": "Boulettes végétariennes",
      "categorie": "PLAT",
      "motsCles": [
        "pois chiche",
        "végétarien"
      ]
    }
  ]
}  
```

---

## 🎨 Design Patterns Implémentés

### 🔔 Observer Pattern
**Problème résolu** : Synchronisation automatique de multiples vues

```java
// Le modèle notifie
facade.notifierObservateurs(); // Toutes les vues se mettent à jour

// Les vues écoutent
public class VueGlobale implements Observateur {
    @Override
    public void reagir() {
        // Mise à jour automatique de l'affichage
        rafraichirVignettes();
    }
}
```

### 🏛️ Facade Pattern
**Problème résolu** : Simplification de l'accès au modèle

```java
facade.ajouter("Burger", 8.50, "burger.jpg", "Description", Categorie.Plat, "viande");
facade.trierArticlesParPrix();
facade.sauvegarderJSON("menu.json");
```

### 📋 Iterator Pattern
**Problème résolu** : Parcours uniforme des collections

```java
// Parcourir tous les articles
for (Article article : facade) {
    System.out.println(article.getNom());
}

// Parcourir les articles filtrés
Iterator<Article> it = facade.iteratorFiltre();
```

---

## 🧪 Tests & Validation

- Tests unitaires pour la logique métier
- Validation des contraintes (prix > 0, champs obligatoires)
- Gestion d'erreurs pour import/export JSON
- Tests d'intégration des vues

---

## 🎓 Concepts Avancés

### Architecture
- **Séparation des préoccupations** : MVC strict
- **Couplage faible** : Communication via Observer
- **Encapsulation** : Accès contrôlé via Facade

### JavaFX
- **FXML** : Séparation interface/logique
- **Binding** : Liaison données/interface
- **CSS** : Stylisation personnalisée

### Programmation
- **Génériques** : `Iterable<Article>`, `Iterator<Article>`
- **Énumérations** : Type-safe pour les catégories
- **Lambda expressions** : Tri et filtrage modernes

---

## 👤 Auteur

**Ousmane DIA**  
Projet académique — Université de Lorraine  
Module Programmation Orientée Objet

---

## 📄 Licence

MIT License — Projet libre de modification et d'utilisation à des fins éducatives.