package org.example.truckfinder2;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminViewController {
    @FXML private TableView<FoodTruck> foodTruckTable;
    @FXML private TableColumn<FoodTruck, String> nomCol;
    @FXML private TableColumn<FoodTruck, String> statutCol;
    @FXML private TableColumn<FoodTruck, Boolean> ouvertCol;
    @FXML private TableColumn<FoodTruck, String> typeCuisineCol;
    @FXML private TableColumn<FoodTruck, String> adresseCol;

    private AdminController adminController;

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        statutCol.setCellValueFactory(new PropertyValueFactory<>("statut"));
        ouvertCol.setCellValueFactory(new PropertyValueFactory<>("estOuvert"));
        typeCuisineCol.setCellValueFactory(new PropertyValueFactory<>("typeCuisine"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        foodTruckTable.setItems(adminController.getFoodTrucks());
        adminController.listerFoodTrucks();
    }

    @FXML
    private void handleValider() {
        FoodTruck selected = foodTruckTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            adminController.validerDemande(selected.getId(), "accepté");
        }
    }

    @FXML
    private void handleRefuser() {
        FoodTruck selected = foodTruckTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            adminController.validerDemande(selected.getId(), "refusé");
        }
    }
}
