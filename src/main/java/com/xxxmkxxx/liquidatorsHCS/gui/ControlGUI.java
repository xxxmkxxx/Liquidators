package com.xxxmkxxx.liquidatorsHCS.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControlGUI {
    public static Stage connectFXML(Class AnyClass, String nameFXML) {
        Stage stage = new Stage();

        URL url = AnyClass.getResource("/" + nameFXML);
        FXMLLoader loader = new FXMLLoader(url);

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        return stage;
    }

    public static Stage connectFXML(Stage stage, Class anyClass, String nameFXML) {
        URL url = anyClass.getResource("/" + nameFXML);
        FXMLLoader loader = new FXMLLoader(url);

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        stage.setScene(scene);

        return stage;
    }

    public static void closeWindow(Button button) {
        Stage lastStage = (Stage) button.getScene().getWindow();
        lastStage.close();
    }

    public static void rollUpWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.hide();
    }

    public static Stage getStage(Button button) {
        return (Stage) button.getScene().getWindow();
    }

    public static Stage getStage(Class anyClass, String nameFXML) {
        return connectFXML(anyClass, nameFXML);
    }
}
