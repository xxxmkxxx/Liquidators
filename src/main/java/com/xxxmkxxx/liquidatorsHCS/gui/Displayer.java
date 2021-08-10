package com.xxxmkxxx.liquidatorsHCS.gui;

import javafx.scene.control.Label;

public class Displayer {
    public static void displayInfo(Label label, String text) {
        if(!text.equals("")) {
            label.setText(text);
            System.out.println(text);
        }
        else {
            label.setText("");
            System.out.println("Поле " + label + " очищено");
        }
    }
}
