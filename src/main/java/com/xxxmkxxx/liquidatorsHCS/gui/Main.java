package com.xxxmkxxx.liquidatorsHCS.gui;

import com.xxxmkxxx.liquidatorsHCS.Skript;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        ControlGUI.connectFXML(stage, getClass(), "loginPage.fxml");
        stage.setTitle("Страница входа");

    }

    public static void runApp(String [] args){
        Application.launch();
    }
}
