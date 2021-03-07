package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
            Stage stage = ControlGUI.connectFXML(getClass(), "mainPage.fxml");
            stage.setTitle("Основная страница");

            ControlGUI.closeWindow(loginButton);
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
        accounts.put("maks11189412", "123789456++*+123");
        accounts.put("Keltek", "1576481320w");
        accounts.put("xxxmkxxx", "1237894560");
        accounts.put("Cyrex12", "qetuo2");
    }

    public void isPressEnter() {
        ControlGUI.getStage(loginButton).getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                    login();
                }
            }
        });
    }
}
