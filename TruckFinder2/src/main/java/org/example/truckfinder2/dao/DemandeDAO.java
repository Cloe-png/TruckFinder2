package org.example.truckfinder2.dao;
import java.sql.*;

public class DemandeDAO {
    private Connection connection;

    public DemandeDAO(Connection connection) {
        this.connection = connection;
    }

    public void creer(int idFoodTruck) throws SQLException {
        String query = "INSERT INTO demandesinscription (id_foodtruck, statut) VALUES (?, 'en attente')";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idFoodTruck);
            stmt.executeUpdate();
        }
    }
}
