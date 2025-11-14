package org.example.truckfinder2.models;
import java.util.List;
import java.util.Map;


public class FoodTruck {
    private int id;
    private String nom;
    private String statut;
    private boolean estOuvert;

    public FoodTruck(int id, String nom, String statut, boolean estOuvert) {
        this.id = id;
        this.nom = nom;
        this.statut = statut;
        this.estOuvert = estOuvert;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getStatut() { return statut; }
    public boolean isEstOuvert() { return estOuvert; }
}
