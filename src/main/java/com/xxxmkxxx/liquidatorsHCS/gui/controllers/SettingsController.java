package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class SettingsController {
    @FXML
    Button cancelButton;
    @FXML
    Button safeButton;
    @FXML
    TreeView rootView;

    public void initTreeView() {
        TreeItem <String> mainSettings = new TreeItem<>("Основные");

        rootView.setRoot(mainSettings);
    }

    public void cancel() {
        ControlGUI.getStage(cancelButton).close();
        ControlGUI.getStage(MainController.class, "mainPage.fxml").show();
    }

    public void safeInfo() {

    }
}
