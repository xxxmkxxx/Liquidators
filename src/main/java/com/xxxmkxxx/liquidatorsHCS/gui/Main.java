package com.xxxmkxxx.liquidatorsHCS.gui;

import com.xxxmkxxx.liquidatorsHCS.gui.controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        ControlGUI loginController = new ControlGUI(getClass(), "loginPage.fxml");
        loginController.connectFXML(stage).show();
        LoginController.lastController = loginController;
    }

    public static void runApp(String [] args){
        Application.launch();
    }
}
