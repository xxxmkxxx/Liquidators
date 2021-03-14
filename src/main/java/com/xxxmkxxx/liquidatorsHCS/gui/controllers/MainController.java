package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.Skript;
import com.xxxmkxxx.liquidatorsHCS.files.Files;
import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.quartz.SchedulerException;

import java.io.*;

public class MainController {
    private Skript skript = new Skript();
    private String pathToProperties = "src/main/resources/properties/config.properties";
    private String pathToFile = "";
    static ControlGUI mainController;

    @FXML
    MenuButton actionListMenuButton;
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
            try (FileInputStream fileInputStream = new FileInputStream(pathToProperties)) {
                Files.properties.load(fileInputStream);
                int indexLastAccount = Integer.parseInt(Files.properties.getProperty("indexLastAccount"));

                skript.setIndexAccaunt(indexLastAccount);
                skript.buildScript();
                skript.runScheduler();
                skript.getTimeLine().play();

                setValueToLabelInAccaunt(indexLastAccount);

                setDisableButtons(true, false, true, false);
            } catch (IOException e) {
                e.printStackTrace();
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
        skript.setIndexAccaunt(0);

        Files.properties.setProperty("indexLastAccount", String.valueOf(0));
        Files.properties.setProperty("lastAccount", "");
        Files.safeChanges(pathToProperties);

        try {
            skript.getScheduler().shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        setDisableButtons(false, true, true, true);

        stateLabel.setText("Скрипт остановлен!!!");
    }

    public void pauseScript() {
        skript.getTimeLine().pause();

        try {
            skript.getScheduler().pauseAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        setDisableButtons(true, true, false, false);

        stateLabel.setText("Скрипт ожидает...");

    }

    public void continueScript() {
        skript.getTimeLine().play();

        try {
            skript.getScheduler().start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        setDisableButtons(true, false, true, false);


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
        ControlGUI settingsController = new ControlGUI(getClass(), "settingsPage.fxml");
        settingsController.connectInnerFXML("settings/settingsMain.fxml").show();
        SettingsController.settingsController = settingsController;
        SettingsController.lastController = mainController;
        mainController.getStage().hide();
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
        tempNickLabel.setText(Files.properties.getProperty("expenseAccount"));
    }
    private void setDisableButtons(boolean disableStartButton, boolean disablePauseButton, boolean disableContinueButton, boolean disableStopButton) {
        actionListMenuButton.getItems().get(0).setDisable(disableStartButton);
        actionListMenuButton.getItems().get(1).setDisable(disablePauseButton);
        actionListMenuButton.getItems().get(2).setDisable(disableContinueButton);
        actionListMenuButton.getItems().get(3).setDisable(disableStopButton);
    }

    public MainController() {
        Files.connectProperties(pathToProperties);
    }
}
