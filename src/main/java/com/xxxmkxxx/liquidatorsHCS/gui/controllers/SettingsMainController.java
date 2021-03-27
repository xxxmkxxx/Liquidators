package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.files.Files;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SettingsMainController {
    @FXML
    TextField expenseAccountField;

    @FXML
    public void initialize() {
        expenseAccountField.setText(Files.properties.getProperty("expenseAccount"));
    }
}
