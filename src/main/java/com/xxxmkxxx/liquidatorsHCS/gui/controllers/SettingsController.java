package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsController {
    static ControlGUI settingsController;
    static ControlGUI lastController;

    @FXML
    Button cancelButton;
    @FXML
    Button safeButton;
    @FXML
    TextField expenseAccountField;

    public void cancel() {
        settingsController.closeWindow();
        lastController.getStage().show();
    }

    public void safeInfo() {

    }
}
