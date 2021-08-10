package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.Config;
import com.xxxmkxxx.liquidatorsHCS.files.FileController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SettingsMainController {
    @FXML
    TextField expenseAccountField;

    @FXML
    public void initialize() {
        expenseAccountField.setText(Config.getField("expenseAccount"));
    }
}
