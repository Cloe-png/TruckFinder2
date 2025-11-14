package org.example.truckfinder2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.truckfinder2.viewmodels.AdminViewModel;
import org.example.truckfinder2.controllers.AdminController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/truckfinder2/views/AdminView.fxml"));
        Parent root = loader.load();
        AdminController controller = loader.getController();
        controller.setAdminViewModel(new AdminViewModel());
        primaryStage.setTitle("TruckFinder - Admin");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
