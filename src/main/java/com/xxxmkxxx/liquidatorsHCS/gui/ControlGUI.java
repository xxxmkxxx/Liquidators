package com.xxxmkxxx.liquidatorsHCS.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControlGUI {
    private Class AnyClass;
    private String nameFXML;
    private Stage stage;
    private FXMLLoader loader;

    private Scene buildScene() {
        Scene scene = null;

        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scene;
    }

    public Stage connectFXML() {
        Stage stage = new Stage();

        stage.setScene(buildScene());

        this.stage = stage;
        return stage;
    }

    public Stage connectFXML(Stage stage) {
        stage.setScene(buildScene());

        this.stage = stage;
        return stage;
    }

    private Scene buildInnerScene(String nameInnerFXML, String id, Scene root, FXMLLoader mainLoader) {
        FXMLLoader innerLoader = createLoader(nameInnerFXML);

        innerLoader.setRoot(mainLoader.getNamespace().get(id));

        try {
            innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }

    public Stage connectInnerFXML(String nameInnerFXML, String id, String mod) {
        switch (mod) {
            case "add": {
                stage.setScene(buildInnerScene(nameInnerFXML, id, stage.getScene(), loader));
                break;
            }
            case "replace": {
                try {
                    FXMLLoader mainLoader = createLoader();
                    stage.setScene(buildInnerScene(nameInnerFXML, id, new Scene(mainLoader.load()), mainLoader));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

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
        return loader;
    }

    private FXMLLoader createLoader() {
        URL url = AnyClass.getResource("/fxml/" + nameFXML);
        loader = new FXMLLoader(url);

        return loader;
    }

    public FXMLLoader createLoader(String nameInnerFXML) {
        URL url = AnyClass.getResource("/fxml/" + nameInnerFXML);

        return new FXMLLoader(url);
    }

    public Stage getStage() {
        return stage;
    }

    public static Stage getStage(Node node) {
        return (Stage)node.getScene().getWindow();
    }

    public Class getContrloller() {
        return AnyClass;
    }

    public ControlGUI(Class AnyClass, String nameFXML) {
        this.AnyClass = AnyClass;
        this.nameFXML = nameFXML;

        createLoader();
    }
}
