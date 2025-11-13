package org.example.truckfinder2.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

public class FoodTruckDAO {
    private Connection connection;
    private Gson gson = new Gson();

    public FoodTruckDAO(Connection connection) {
        this.connection = connection;
    }

    public int creer(int idUtilisateur, String nom, String description, String typeCuisine,
                     String adresse, String telephone, String logo) throws SQLException {
        String query = "INSERT INTO foodtrucks (id_utilisateur, nom_foodtruck, description, type_cuisine, " +
                "adresse, telephone, logo, statut, est_ouvert) VALUES (?, ?, ?, ?, ?, ?, ?, 'en attente', false)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, idUtilisateur);
            stmt.setString(2, nom);
            stmt.setString(3, description);
            stmt.setString(4, typeCuisine);
            stmt.setString(5, adresse);
            stmt.setString(6, telephone);
            stmt.setString(7, logo);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    public void updateMenu(int idFoodTruck, List<Map<String, Object>> menu) throws SQLException {
        String menuJson = gson.toJson(menu);
        String query = "UPDATE foodtrucks SET menu = ? WHERE id_foodtruck = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, menuJson);
            stmt.setInt(2, idFoodTruck);
            stmt.executeUpdate();
        }
    }

    public List<FoodTruck> listerFoodTrucks() throws SQLException {
        List<FoodTruck> foodTrucks = new ArrayList<>();
        String query = "SELECT * FROM foodtrucks ORDER BY date_creation DESC";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FoodTruck ft = new FoodTruck(
                        rs.getInt("id_foodtruck"),
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom_foodtruck"),
                        rs.getString("description"),
                        rs.getString("type_cuisine"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getString("logo"),
                        rs.getString("statut"),
                        rs.getBoolean("est_ouvert"),
                        gson.fromJson(rs.getString("menu"), List.class)
                );
                foodTrucks.add(ft);
            }
        }
        return foodTrucks;
    }

    public void validerDemande(int idFoodTruck, String statut) throws SQLException {
        String query = "UPDATE foodtrucks SET statut = ? WHERE id_foodtruck = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, statut);
            stmt.setInt(2, idFoodTruck);
            stmt.executeUpdate();
        }
    }
}
