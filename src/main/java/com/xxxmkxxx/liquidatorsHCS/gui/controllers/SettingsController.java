package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SettingsController {
    static ControlGUI settingsController;
    static ControlGUI lastController;

    @FXML
    Button cancelButton;
    @FXML
    Button safeButton;

    public void cancel() {
        settingsController.closeWindow();
        lastController.getStage().show();
    }

    public void safeInfo() {

    }
}
