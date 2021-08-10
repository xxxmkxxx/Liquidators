package com.xxxmkxxx.liquidatorsHCS.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        ControlGUI loginController = new ControlGUI(getClass(), "loginPage.fxml");
        loginController.connectFXML(stage).show();
    }

    public static void runApp(String [] args){
        Application.launch();
    }
}
