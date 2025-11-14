package org.example.truckfinder2.dao;
import org.example.truckfinder2.models.Utilisateur;

import java.sql.*;

public class UtilisateurDAO {
    private Connection connection;

    public UtilisateurDAO(Connection connection) {
        this.connection = connection;
    }

    public int inscrire(String nom, String email, String motDePasse) throws SQLException {
        String query = "INSERT INTO utilisateurs (nom, email, mot_de_passe, role) VALUES (?, ?, ?, 'foodtruck')";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nom);
            stmt.setString(2, email);
            stmt.setString(3, motDePasse);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    public Utilisateur trouverParEmail(String email) throws SQLException {
        String query = "SELECT * FROM utilisateurs WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
            }
        }
        return null;
    }
}
