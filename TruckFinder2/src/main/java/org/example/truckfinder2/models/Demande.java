package org.example.truckfinder2.models;

public class Demande {
    private int id;
    private int idFoodTruck;
    private String statut; // "en attente", "accepté", "refusé"

    public Demande(int id, int idFoodTruck, String statut) {
        this.id = id;
        this.idFoodTruck = idFoodTruck;
        this.statut = statut;
    }

    // Getters et setters...
    public int getId() { return id; }
    public int getIdFoodTruck() { return idFoodTruck; }
    public String getStatut() { return statut; }
}
