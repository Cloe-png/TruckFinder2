package org.example.truckfinder2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginViewController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private AuthController authController;
    private Stage stage;
    private Connection connection;

    // Méthode pour injecter le contrôleur d'authentification et la connexion BD
    public void setAuthController(AuthController authController, Connection connection) {
        this.authController = authController;
        this.connection = connection;
    }

    // Méthode pour injecter la fenêtre principale (Stage)
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Gère l'action du bouton "Se connecter"
    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String motDePasse = passwordField.getText().trim();

        if (email.isEmpty() || motDePasse.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        Utilisateur utilisateur = authController.connecter(email, motDePasse);
        if (utilisateur != null) {
            try {
                if (utilisateur.getRole().equals("admin")) {
                    // Charge la vue Admin
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminView.fxml"));
                    Parent root = loader.load();
                    AdminViewController adminController = loader.getController();
                    adminController.setAdminController(new AdminController(connection));
                    stage.setScene(new Scene(root, 800, 600));
                    stage.setTitle("Admin - TruckFinder");
                } else if (utilisateur.getRole().equals("foodtruck")) {
                    // Vérifie si le foodtruck est approuvé
                    FoodTruckDAO foodTruckDAO = new FoodTruckDAO(connection);
                    FoodTruck foodTruck = foodTruckDAO.getFoodtruckData(utilisateur.getId());
                    if (foodTruck != null && foodTruck.getStatut().equals("accepté")) {
                        // Charge la vue FoodTruck
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FoodTruckView.fxml"));
                        Parent root = loader.load();
                        FoodTruckViewController foodTruckController = loader.getController();
                        foodTruckController.setFoodTruckController(new FoodTruckController(connection), utilisateur.getId());
                        stage.setScene(new Scene(root, 800, 600));
                        stage.setTitle("FoodTruck - Dashboard");
                    } else {
                        messageLabel.setText("Votre compte foodtruck n'est pas encore approuvé.");
                    }
                }
            } catch (Exception e) {
                messageLabel.setText("Erreur lors du chargement de la vue: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Email ou mot de passe incorrect.");
        }
    }

    // Gère l'action du bouton "S'inscrire"
    @FXML
    private void handleRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RegisterView.fxml"));
            Parent root = loader.load();
            RegisterViewController registerController = loader.getController();
            registerController.setAuthController(authController, connection);
            registerController.setStage(stage);
            stage.setScene(new Scene(root, 400, 500));
            stage.setTitle("Inscription - TruckFinder");
        } catch (Exception e) {
            messageLabel.setText("Erreur lors du chargement de la vue d'inscription.");
            e.printStackTrace();
        }
    }
}
