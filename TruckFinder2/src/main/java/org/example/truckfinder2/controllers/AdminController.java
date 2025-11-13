package org.example.truckfinder2.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.SQLException;

public class AdminController {
    private FoodTruckDAO foodTruckDAO;
    private ObservableList<FoodTruck> foodTrucks;

    public AdminController(Connection connection) {
        this.foodTruckDAO = new FoodTruckDAO(connection);
        this.foodTrucks = FXCollections.observableArrayList();
    }

    public void listerFoodTrucks() {
        try {
            foodTrucks.setAll(foodTruckDAO.listerFoodTrucks());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validerDemande(int idFoodTruck, String statut) {
        try {
            foodTruckDAO.validerDemande(idFoodTruck, statut);
            listerFoodTrucks(); // Rafraîchit la liste
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<FoodTruck> getFoodTrucks() {
        return foodTrucks;
    }
}
