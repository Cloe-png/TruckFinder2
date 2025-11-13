package org.example.truckfinder2.controllers;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AuthController {
    private UtilisateurDAO utilisateurDAO;
    private FoodTruckDAO foodTruckDAO;
    private DemandeDAO demandeDAO;

    public AuthController(Connection connection) {
        this.utilisateurDAO = new UtilisateurDAO(connection);
        this.foodTruckDAO = new FoodTruckDAO(connection);
        this.demandeDAO = new DemandeDAO(connection);
    }

    public boolean inscrire(String nom, String email, String motDePasse, String nomFoodTruck,
                            String description, String typeCuisine, String adresse,
                            String telephone, String logo) {
        try {
            int idUtilisateur = utilisateurDAO.inscrire(nom, email, motDePasse);
            int idFoodTruck = foodTruckDAO.creer(idUtilisateur, nomFoodTruck, description, typeCuisine,
                    adresse, telephone, logo);
            List<Map<String, Object>> menuBase = List.of(
                    Map.of("nom", "Menu du jour", "description", "Notre spécialité du moment", "prix", 12.50, "image", "https://via.placeholder.com/150?text=Menu+du+jour"),
                    Map.of("nom", "Boisson", "description", "Boisson au choix", "prix", 2.50, "image", "https://via.placeholder.com/150?text=Boisson"),
                    Map.of("nom", "Dessert", "description", "Dessert maison", "prix", 4.00, "image", "https://via.placeholder.com/150?text=Dessert")
            );
            foodTruckDAO.updateMenu(idFoodTruck, menuBase);
            demandeDAO.creer(idFoodTruck);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Utilisateur connecter(String email, String motDePasse) {
        try {
            Utilisateur utilisateur = utilisateurDAO.trouverParEmail(email);
            if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {
                return utilisateur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
