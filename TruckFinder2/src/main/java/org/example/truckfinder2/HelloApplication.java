package org.example.truckfinder2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        AuthController authController = new AuthController(connection);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
        Parent root = loader.load();
        LoginViewController controller = loader.getController();
        controller.setAuthController(authController);
        controller.setStage(primaryStage);

        primaryStage.setTitle("TruckFinder - Connexion");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
