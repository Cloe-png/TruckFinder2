package org.example.truckfinder2.viewmodels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.truckfinder2.dao.DatabaseConnection;
import org.example.truckfinder2.dao.FoodTruckDAO;
import org.example.truckfinder2.models.FoodTruck;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminViewModel {
    private ObservableList<FoodTruck> foodTrucksOuverts = FXCollections.observableArrayList();
    private ObservableList<FoodTruck> tousFoodTrucks = FXCollections.observableArrayList();
    private FoodTruckDAO foodTruckDAO;

    public AdminViewModel() {
        Connection connection = DatabaseConnection.getConnection();
        this.foodTruckDAO = new FoodTruckDAO(connection);
        chargerFoodTrucksOuverts();
        chargerTousFoodTrucks();
    }

    public void chargerFoodTrucksOuverts() {
        try {
            foodTrucksOuverts.setAll(foodTruckDAO.listerFoodTrucksOuverts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chargerTousFoodTrucks() {
        try {
            tousFoodTrucks.setAll(foodTruckDAO.listerTousFoodTrucks());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public ObservableList<FoodTruck> getFoodTrucksOuverts() { return foodTrucksOuverts; }
    public ObservableList<FoodTruck> getTousFoodTrucks() { return tousFoodTrucks; }
}
