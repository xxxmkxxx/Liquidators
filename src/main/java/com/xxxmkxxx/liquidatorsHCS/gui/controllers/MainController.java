package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.Skript;
import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.quartz.SchedulerException;

import java.io.*;

public class MainController {
    private Skript skript = new Skript();
    private String pathToFile = "";

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
    @FXML
    Label errorLabel;
    @FXML
    Label countAccauntLabel;
    @FXML
    Label allTimeToFarmLabel;
    @FXML
    Label countAllMlkLabel;
    @FXML
    Label numberAccauntGroupLabel;
    @FXML
    Label tempNickLabel;
    @FXML
    Label timeAccauntLabel;

    public void startScript() {
        if(!pathToFile.equals("")) {
            if (new File("src/main/java/com/xxxmkxxx/liquidatorsHCS/files/" + "lastAccaunt.txt").exists()) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/com/xxxmkxxx/liquidatorsHCS/files/" + "lastAccaunt.txt"))) {
                    String numberAccaunt = bufferedReader.readLine();
                    if (numberAccaunt != null) {
                        skript.setIndexAccaunt(Integer.parseInt(numberAccaunt));
                        skript.runScheduler();
                        skript.getTimeLine().play();
                        setValueToLabelInAccaunt(Integer.parseInt(numberAccaunt));
                    } else {
                        skript.runScheduler();
                        skript.getTimeLine().play();
                        setValueToLabelInAccaunt(0);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                skript.runScheduler();
                skript.getTimeLine().play();
                setValueToLabelInAccaunt(0);
            }

            stateLabel.setText("Скрипт запущен...");
        }
        else{
            errorLabel.setText("Вы не выбрали файл!!!");
        }
    }

    public void stopScript() {
        setValueToLabelInAccaunt();
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
        try {
            skript.getScheduler().pauseAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        stateLabel.setText("Скрипт ожидает...");
    }

    public void continueScript() {
        skript.getTimeLine().play();
        try {
            skript.getScheduler().start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        stateLabel.setText("Скрипт запущен...");
    }

    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");

        pathToFile = fileChooser.showOpenDialog(new Stage()).getAbsolutePath();

        skript.setPathToMail(pathToFile);
        skript.buildScript();

        setValueToLabelInInfo(skript.getCountAccaunt(), skript.getAllTime());
        errorLabel.setText("");

    }

    public void updateInfo() {
        if(pathToFile.equals("")) {
            errorLabel.setText("Вы не выбрали файл!!!");
        }
        else {
            skript.buildScript();
            setValueToLabelInInfo(skript.getCountAccaunt(), skript.getAllTime());
            errorLabel.setText("");
        }
    }

    public void openSettings() {
        ControlGUI.connectFXML(getClass(), "settingsPage.fxml").show();
        ControlGUI.getStage(updateButton).close();
    }

    private void setValueToLabelInAccaunt(int numberAccaunt) {
        currentNumberAccauntLabel.setText(String.valueOf(numberAccaunt + 1));
        currentNickAccauntLabel.setText(skript.getListMail().get(numberAccaunt));
    }
    private void setValueToLabelInAccaunt() {
        currentNumberAccauntLabel.setText("");
        currentNickAccauntLabel.setText("");
    }
    private void setValueToLabelInInfo(int countAccaunt, int time) {
        countAccauntLabel.setText(String.valueOf(countAccaunt));
        allTimeToFarmLabel.setText(String.valueOf((time / (60 * 60))));
        countAllMlkLabel.setText(String.valueOf(countAccaunt * 10));
        timeAccauntLabel.setText(String.valueOf((time / 60) / countAccaunt));
    }

}
