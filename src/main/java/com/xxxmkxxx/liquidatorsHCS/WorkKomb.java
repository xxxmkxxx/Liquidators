package com.xxxmkxxx.liquidatorsHCS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class WorkKomb implements Job {
    private Timeline timeLine;
    private String pathToGame;
    private String mail;
    private String pathToJava;
    Mouse mouse = new Mouse();
    KeyBoard keyBoard = new KeyBoard();

    public void startSection(String pathToJava, String pathToGame, String mail){
        this.pathToJava = pathToJava;
        this.pathToGame = pathToGame;
        this.mail = mail;

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time), e -> {
            Runtime run = Runtime.getRuntime();
            Process process = null;

            try {
                process = run.exec(pathToJava + " " + pathToGame);
            }
            catch (Exception er) {
                System.out.println(er);
            }
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 30), e -> {
            keyBoard.pressKey("Ctrl + A");
            keyBoard.pasteOfBoofer(mail);
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 1), e -> {
            keyBoard.pressKey("Tab");
            keyBoard.pasteOfBoofer("1237894560");
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 1), e -> {
            mouse.moveXY(Skript.arrCoords[0][0], Skript.arrCoords[0][1]);
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 6), e -> {
            mouse.moveXY(Skript.arrCoords[1][0], Skript.arrCoords[1][1]);
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 7), e -> {
            mouse.moveXY(Skript.arrCoords[1][0], Skript.arrCoords[1][1]);
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += (2 * 60))));
    }

    public void stayAFKSection(int timeAFK, int minits){
        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 1), e -> {
            mouse.moveXY(Skript.arrCoords[2][0], Skript.arrCoords[2][1]); //Кнопка DayZ 3
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 2)));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 1), e -> {
            mouse.moveXY(Skript.arrCoords[3][0], Skript.arrCoords[3][1]); //Кнопка Играть
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 6), ep -> {
            int countIteracion = timeAFK/minits;
            for(int i = 0; i < countIteracion; i++) {
                timeLine.getKeyFrames().add(new KeyFrame(Duration.millis(Skript.time += 1000 * ((60 * minits) / 2)), e -> keyBoard.pressKey("W")));

                timeLine.getKeyFrames().add(new KeyFrame(Duration.millis(Skript.time += 1000 * ((60 * minits) / 2)), e -> keyBoard.pressKey("S")));

            }
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += (timeAFK * 60))));
    }

    public void exitSection(){
        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 4), e -> keyBoard.pressKey("Esc")));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 2), e -> {
            mouse.moveXY(Skript.arrCoords[6][0], Skript.arrCoords[6][1]); //Кнопка Back
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 1), e -> {
            mouse.moveXY(Skript.arrCoords[4][0], Skript.arrCoords[4][1]); //Кнопка Disconnect
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 2), e -> {
            mouse.moveXY(Skript.arrCoords[5][0], Skript.arrCoords[5][1]); //Кнопка Выход
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 7), e -> {
            mouse.moveXY(Skript.arrCoords[7][0], Skript.arrCoords[7][1]); //Кнопка Разлогиниться
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 2), e -> {
            mouse.moveXY(Skript.arrCoords[8][0], Skript.arrCoords[8][1]); //Кнопка да
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 4), e -> {
            mouse.moveXY(Skript.arrCoords[9][0], Skript.arrCoords[9][1]); //Кнопка [x]
            mouse.clickButton();
        }));

        timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time += 3)));
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        restartSection();
    }

    public void restartSection(){
        timeLine.stop();
        timeLine = new Timeline();

        exitSection();
        startSection(pathToJava, pathToGame, mail);
    }

    public WorkKomb(Timeline timeLine) {
        this.timeLine = timeLine;
    }
}
