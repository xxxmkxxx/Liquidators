package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.Skript;
import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.*;

public class MainController {
    Skript skript = new Skript();

    @FXML
    Button updateButton;

    public void startScript() {
        if(new File("script").exists()){
            skript.runScript();
        }
        else{
            skript.runScript();
        }
    }

    public void stopScript() {
        System.exit(0);
    }

    public void pauseScript() {
        skript.getTimeLine().pause();
    }

    public void continueScript() {
        skript.getTimeLine().play();
    }
}
