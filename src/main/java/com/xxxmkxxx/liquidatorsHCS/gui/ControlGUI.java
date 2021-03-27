package com.xxxmkxxx.liquidatorsHCS.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControlGUI {
    private Class AnyClass;
    private String nameFXML;
    private Stage stage;
    private FXMLLoader loader;


    public Stage connectFXML() {
        Stage stage = new Stage();

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
        stage.show();

        this.stage = stage;
        return stage;
    }

    public Stage connectFXML(Stage stage) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);

        this.stage = stage;
        return stage;
    }

    public Stage connectInnerFXML(String nameInnerFXML) {
        Stage stage = new Stage();

        URL url = AnyClass.getResource("/fxml/" + nameFXML);
        FXMLLoader mainLoader = new FXMLLoader(url);

        Scene scene = null;
        try {
            scene = new Scene(mainLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = AnyClass.getResource("/fxml/" + nameInnerFXML);
        FXMLLoader innerLoader = new FXMLLoader(url);

        innerLoader.setRoot(mainLoader.getNamespace().get("insertionPoint"));
        try {
            innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);

        this.stage = stage;
        return stage;
    }

    public void connectCSS(String nameCSS) {
        stage.getScene().getStylesheets().add((AnyClass.getResource("/css/" + nameCSS)).toExternalForm());
    }

    public void closeWindow() {
        stage.close();
    }

    public void rollUpWindow() {
        stage.hide();
    }

    public FXMLLoader getLoader() {
        URL url = AnyClass.getResource("/fxml/" + nameFXML);
        loader = new FXMLLoader(url);

        return loader;
    }

    public Stage getStage() {
        return stage;
    }

    public Class getControoller() {
        return AnyClass;
    }

    public ControlGUI(Class AnyClass, String nameFXML) {
        this.AnyClass = AnyClass;
        this.nameFXML = nameFXML;

        getLoader();
    }
}
