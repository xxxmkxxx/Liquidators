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
            skript = upClass(skript);
            skript.runScript();
        }
        else{
            skript.runScript();
        }
    }

    public void stopScript() {
        safeClass(skript);

        System.exit(0);
    }

    public void pauseScript() {
        skript.getTimeLine().pause();
    }

    public void continueScript() {
        skript.getTimeLine().play();
    }

    public static void safeClass(Skript skript){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("script");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(skript);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Skript upClass(Skript script){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Skript tempObject = null;

        try {
            fileInputStream = new FileInputStream("script");
            objectInputStream = new ObjectInputStream(fileInputStream);
            tempObject = (Skript) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return tempObject;
    }
}
