package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private Map <String, String> accounts = new HashMap();

    @FXML
    TextField loginField;
    @FXML
    PasswordField passField;
    @FXML
    Button loginButton;
    @FXML
    Label errorLabel;

    public void login() {
        if(checkPass()) {
            ControlGUI.getStage(loginField).close();

            ControlGUI mainController = new ControlGUI(getClass(), "mainPage.fxml");
            Stage stage = mainController.connectFXML();

            stage.show();
            stage.setTitle("Основная страница");
        }
        else {
            errorLabel.setText("Пароль неверный!");
        }
    }

    private boolean checkPass() {
        setPassword();
        boolean coincidence = false;

        if(accounts.containsKey(loginField.getText())) {
            if(passField.getText().equals(accounts.get(loginField.getText()))) {
                coincidence = true;
            }
        }
        else {
            errorLabel.setText("Такого логина не существует!");
        }
        return coincidence;
    }

    private void setPassword() {
        accounts.put("maks11189412", "157345d+7g");
        accounts.put("Keltek", "1576481320w");
        accounts.put("xxxmkxxx", "1237894560q++");
        accounts.put("Cyrex12", "qetuo2");
    }
}
