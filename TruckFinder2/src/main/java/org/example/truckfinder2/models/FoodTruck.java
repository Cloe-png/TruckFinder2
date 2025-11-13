package org.example.truckfinder2.models;
import java.util.List;
import java.util.Map;

public class FoodTruck {
    private int id;
    private int idUtilisateur;
    private String nom;
    private String description;
    private String typeCuisine;
    private String adresse;
    private String telephone;
    private String logo;
    private String statut; // "en attente", "accepté", "refusé"
    private boolean estOuvert;
    private List<Map<String, Object>> menu;

    public FoodTruck(int id, int idUtilisateur, String nom, String description, String typeCuisine,
                     String adresse, String telephone, String logo, String statut,
                     boolean estOuvert, List<Map<String, Object>> menu) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.description = description;
        this.typeCuisine = typeCuisine;
        this.adresse = adresse;
        this.telephone = telephone;
        this.logo = logo;
        this.statut = statut;
        this.estOuvert = estOuvert;
        this.menu = menu;
    }

    // Getters et setters...
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getStatut() { return statut; }
    public boolean isEstOuvert() { return estOuvert; }
    public List<Map<String, Object>> getMenu() { return menu; }
}
