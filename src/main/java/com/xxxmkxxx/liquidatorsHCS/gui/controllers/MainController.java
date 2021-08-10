package com.xxxmkxxx.liquidatorsHCS.gui.controllers;

import com.xxxmkxxx.liquidatorsHCS.Config;
import com.xxxmkxxx.liquidatorsHCS.Skript;
import com.xxxmkxxx.liquidatorsHCS.files.FileController;
import com.xxxmkxxx.liquidatorsHCS.gui.ControlGUI;
import com.xxxmkxxx.liquidatorsHCS.gui.Displayer;
import com.xxxmkxxx.liquidatorsHCS.gui.Main;
import com.xxxmkxxx.liquidatorsHCS.properties.PropertiesController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.quartz.SchedulerException;

public class MainController {
    private Skript skript = new Skript();
    private String pathToFile = "";
    private boolean isStarted = false;


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
            isStarted = true;

            int indexLastAccount = Integer.parseInt(Config.getField("indexLastAccount"));

            skript.setIndexAccaunt(indexLastAccount);
            skript.buildScript();
            skript.runScheduler();
            skript.getTimeLine().play();

            setValueToLabelInAccaunt(indexLastAccount);

            setDisableButtons(true, false, true, false);

            Displayer.displayInfo(stateLabel, "Скрипт запущен...");
        }
        else {
            Displayer.displayInfo(errorLabel, "Вы не выбрали файл!!!");
        }
    }

    public void stopScript() {
        isStarted = false;
        setValueToLabelInAccaunt();
        skript.getTimeLine().stop();
        skript.setIndexAccaunt(0);

        try {
            skript.getScheduler().shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        Config.setField("indexLastAccount", String.valueOf(0));
        Config.setField("lastAccount", "");
        Config.safe();

        setDisableButtons(false, true, true, true);

        Displayer.displayInfo(stateLabel, "Скрипт остановлен!!!");
    }

    public void pauseScript() {
        skript.getTimeLine().pause();

        try {
            skript.getScheduler().pauseAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        setDisableButtons(true, true, false, false);

        Displayer.displayInfo(stateLabel, "Скрипт ожидает...");
    }

    public void continueScript() {
        skript.getTimeLine().play();

        try {
            skript.getScheduler().start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        setDisableButtons(true, false, true, false);

        Displayer.displayInfo(stateLabel, "Скрипт продолжает работу...");
    }

    public void setInfo() {
        if(isStarted) {
            int indexLastAccount = Integer.parseInt(Config.getField("indexLastAccount"));
            setValueToLabelInAccaunt(indexLastAccount);
        }
    }

    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");

        pathToFile = fileChooser.showOpenDialog(new Stage()).getAbsolutePath();

        skript.setPathToMail(pathToFile);
        skript.buildScript();

        setValueToLabelInInfo(skript.getCountAccaunt(), skript.getAllTime(), Config.getField("expenseAccount"));

        Displayer.displayInfo(errorLabel, "");

    }

    public void updateInfo() {
        if(pathToFile.equals("")) {
            Displayer.displayInfo(errorLabel, "Вы не выбрали файл!!!");
        }
        else {
            skript.buildScript();
            setValueToLabelInInfo(skript.getCountAccaunt(), skript.getAllTime(), Config.getField("expenseAccount"));
            Displayer.displayInfo(errorLabel, "");
        }
    }

    public void openSettings() {
        ControlGUI settingsController = new ControlGUI(getClass(), "settingsPage.fxml");
        settingsController.connectInnerFXML("settings/settingsMain.fxml", "insertionPoint", "add").show();
    }

    public void setValueToLabelInAccaunt(int numberAccaunt) {
        currentNumberAccauntLabel.setText(String.valueOf(numberAccaunt + 1));
        currentNickAccauntLabel.setText(skript.getListMail().get(numberAccaunt));
    }
    private void setValueToLabelInAccaunt() {
        currentNumberAccauntLabel.setText("");
        currentNickAccauntLabel.setText("");
    }
    private void setValueToLabelInInfo(int countAccaunt, int time, String expenseAccount) {
        countAccauntLabel.setText(String.valueOf(countAccaunt));
        allTimeToFarmLabel.setText(String.valueOf((time / (60 * 60))));
        countAllMlkLabel.setText(String.valueOf(countAccaunt * 10));
        timeAccauntLabel.setText(String.valueOf((time / 60) / countAccaunt));
        tempNickLabel.setText(expenseAccount);
    }
    private void setDisableButtons(boolean disableStartButton, boolean disablePauseButton, boolean disableContinueButton, boolean disableStopButton) {
        actionListMenuButton.getItems().get(0).setDisable(disableStartButton);
        actionListMenuButton.getItems().get(1).setDisable(disablePauseButton);
        actionListMenuButton.getItems().get(2).setDisable(disableContinueButton);
        actionListMenuButton.getItems().get(3).setDisable(disableStopButton);
    }
}
