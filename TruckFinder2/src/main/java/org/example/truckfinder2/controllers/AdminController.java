package org.example.truckfinder2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.truckfinder2.models.FoodTruck;
import org.example.truckfinder2.viewmodels.AdminViewModel;

public class AdminController {
    @FXML private TableView<FoodTruck> foodTrucksOuvertsTable;
    @FXML private TableColumn<FoodTruck, String> nomCol;
    @FXML private TableColumn<FoodTruck, String> statutCol;
    @FXML private TableColumn<FoodTruck, Boolean> ouvertCol;

    @FXML private TableView<FoodTruck> tousFoodTrucksTable;
    @FXML private TableColumn<FoodTruck, String> nomColTous;
    @FXML private TableColumn<FoodTruck, String> statutColTous;
    @FXML private TableColumn<FoodTruck, Boolean> ouvertColTous;

    private AdminViewModel adminViewModel;

    public void setAdminViewModel(AdminViewModel adminViewModel) {
        this.adminViewModel = adminViewModel;

        // Configuration des colonnes pour les foodtrucks ouverts
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        statutCol.setCellValueFactory(new PropertyValueFactory<>("statut"));
        ouvertCol.setCellValueFactory(new PropertyValueFactory<>("estOuvert"));
        foodTrucksOuvertsTable.setItems(adminViewModel.getFoodTrucksOuverts());

        // Configuration des colonnes pour tous les foodtrucks
        nomColTous.setCellValueFactory(new PropertyValueFactory<>("nom"));
        statutColTous.setCellValueFactory(new PropertyValueFactory<>("statut"));
        ouvertColTous.setCellValueFactory(new PropertyValueFactory<>("estOuvert"));
        tousFoodTrucksTable.setItems(adminViewModel.getTousFoodTrucks());
    }
}
