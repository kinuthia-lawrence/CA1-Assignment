package com.larrykin.ca1assignment.controllers;

import com.larrykin.ca1assignment.enums.OvenMode;
import com.larrykin.ca1assignment.models.Cake;
import com.larrykin.ca1assignment.services.OvenManager;
import com.larrykin.ca1assignment.utils.DateFormatter;
import com.larrykin.ca1assignment.utils.InputValidator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ComboBox<String> cakeTypeComboBox;
    @FXML
    private TextField weightField;
    @FXML
    private DatePicker bestBeforeDatePicker;
    @FXML
    private Label statusLabel;
    @FXML
    private ListView<String> cakesListView;
    @FXML
    private Label topCakeLabel;
    @FXML
    private ToggleGroup ovenModeGroup;
    @FXML
    private RadioButton stackModeRadio;
    @FXML
    private RadioButton queueModeRadio;
    @FXML
    private Label ovenCapacityLabel;

    private OvenManager ovenManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the oven manager with capacity of 5
        ovenManager = new OvenManager(5, OvenMode.STACK);

        // Set up the cake types combo box
        cakeTypeComboBox.setItems(FXCollections.observableArrayList(
                "Pineapple", "Strawberry", "Chocolate", "Vanilla", "Plain"));

        // Set default date to today
        bestBeforeDatePicker.setValue(LocalDate.now());

        // Set up radio buttons
        stackModeRadio.setUserData(OvenMode.STACK);
        queueModeRadio.setUserData(OvenMode.QUEUE);
        stackModeRadio.setSelected(true);

        ovenModeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ovenManager.setMode((OvenMode) newValue.getUserData());
                updateOvenDisplay();
            }
        });

        // Update display
        updateOvenDisplay();
    }

    @FXML
    private void handleAddCake() {
        // Validate input
        String cakeType = cakeTypeComboBox.getValue();
        String weightStr = weightField.getText();
        LocalDate bestBeforeDate = bestBeforeDatePicker.getValue();

        if (!InputValidator.isValidCakeType(cakeType)) {
            showError("Please select a valid cake type.");
            return;
        }

        if (!InputValidator.isValidWeight(weightStr)) {
            showError("Please enter a valid weight (positive number).");
            return;
        }

        if (!InputValidator.isValidBestBeforeDate(bestBeforeDate)) {
            showError("Best before date must be between today and 2 weeks from now.");
            return;
        }

        // Create cake object
        double weight = Double.parseDouble(weightStr);
        Cake cake = new Cake(cakeType, weight, bestBeforeDate);

        // Add to oven
        if (ovenManager.addCake(cake)) {
            statusLabel.setText("Added " + cakeType + " cake to the oven.");
            clearInputFields();
            updateOvenDisplay();
        } else {
            showError("No more capacity! The oven is full.");
        }
    }

    @FXML
    private void handleRemoveCake() {
        Cake removedCake = ovenManager.removeCake();
        if (removedCake != null) {
            statusLabel.setText("Removed " + removedCake.getName() + " cake from the oven.");
            updateOvenDisplay();
        } else {
            showError("The oven is empty. Nothing to remove.");
        }
    }

    private void updateOvenDisplay() {
        // Update capacity label
        ovenCapacityLabel.setText(ovenManager.getCurrentSize() + " / " + ovenManager.getCapacity());

        // Update top cake label
        Cake topCake = ovenManager.peekTopCake();
        if (topCake != null) {
            topCakeLabel.setText("Top cake: " + topCake.getName() + " - " + topCake.getWeight() + "g");
        } else {
            topCakeLabel.setText("No cakes in the oven");
        }

        // Update cakes list
        List<Cake> cakes = ovenManager.getAllCakes();
        cakesListView.getItems().clear();

        if (cakes.isEmpty()) {
            cakesListView.getItems().add("The oven is empty");
        } else {
            for (Cake cake : cakes) {
                cakesListView.getItems().add(cake.getName() +
                        " - Weight: " + cake.getWeight() + "g" +
                        " - Best Before: " + DateFormatter.formatDate(cake.getBestBeforeDate()) +
                        " - Added at: " + DateFormatter.formatDateTime(cake.getTimeAddedToOven()));
            }
        }
    }

    private void clearInputFields() {
        cakeTypeComboBox.getSelectionModel().clearSelection();
        weightField.clear();
        bestBeforeDatePicker.setValue(LocalDate.now());
    }

    private void showError(String message) {
        statusLabel.setText("Error: " + message);
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}