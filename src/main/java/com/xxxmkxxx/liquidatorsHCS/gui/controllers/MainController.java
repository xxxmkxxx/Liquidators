package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.Skript;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.quartz.SchedulerException;

import java.io.*;

public class MainController {
    Skript skript = new Skript();

    @FXML
    Button updateButton;
    @FXML
    Label stateLabel;
    @FXML
    Label currentNumberAccauntLabel;
    @FXML
    Label currentNickAccauntLabel;
    @FXML
    Label currentElapsedTimeLabel;

    public void startScript() {
        if(new File("src/main/java/com/xxxmkxxx/liquidatorsHCS/files/" + "lastAccaunt.txt").exists()) {
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/com/xxxmkxxx/liquidatorsHCS/files/" + "lastAccaunt.txt"))) {
                String numberAccaunt = bufferedReader.readLine();
                if(numberAccaunt != null) {
                    skript.runScript(Integer.parseInt(numberAccaunt));
                    setValueToLabel(Integer.parseInt(numberAccaunt));
                }
                else {
                    skript.runScript(0);
                    setValueToLabel(0);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            skript.runScript(0);
            setValueToLabel(0);
        }

        stateLabel.setText("Скрипт запущен...");
    }

    public void stopScript() {
        skript.getTimeLine().stop();
        try {
            skript.getScheduler().shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        stateLabel.setText("Скрипт остановлен!!!");
    }

    public void pauseScript() {
        skript.getTimeLine().pause();
        stateLabel.setText("Скрипт ожидает...");
    }

    public void continueScript() {
        skript.getTimeLine().play();
        stateLabel.setText("Скрипт запущен...");
    }

    private void setValueToLabel(int numberAccaunt) {
        currentNumberAccauntLabel.setText(String.valueOf(numberAccaunt + 1));
        currentNickAccauntLabel.setText(skript.getListMail().get(numberAccaunt));
    }
    private void setValueToLabel() {
        currentNumberAccauntLabel.setText("");
        currentNickAccauntLabel.setText("");
    }
}
