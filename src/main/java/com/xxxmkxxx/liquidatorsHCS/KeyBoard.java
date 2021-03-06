package com.xxxmkxxx.liquidatorsHCS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.robot.Robot;
import javafx.util.Duration;

public class KeyBoard{
    private Robot robot = new Robot();

    //Метод для работы с нажатием клавиш или комбинаций
    public void pressKey(String key){
        switch(key){
            case "Ctrl + A":{
                robot.keyPress(KeyCode.CONTROL);
                robot.keyPress(KeyCode.A);
                robot.keyRelease(KeyCode.A);
                robot.keyRelease(KeyCode.CONTROL);

                break;
            }
            case "Tab":{
                robot.keyPress(KeyCode.TAB);
                robot.keyRelease(KeyCode.TAB);

                break;
            }
            case "Esc":{
                robot.keyPress(KeyCode.ESCAPE);
                robot.keyRelease(KeyCode.ESCAPE);

                break;
            }
            case "W":{
                Timeline timeLine = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> robot.keyPress(KeyCode.W)),
                        new KeyFrame(Duration.seconds(3), e -> robot.keyRelease(KeyCode.W))
                );
                timeLine.play();

                break;
            }
            case "S":{
                Timeline timeLine = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> robot.keyPress(KeyCode.S)),
                        new KeyFrame(Duration.seconds(3), e -> robot.keyRelease(KeyCode.S))
                );
                timeLine.play();

                break;
            }
        }
    }

    //Вставка текста из буфера обмена
    public void pasteOfBoofer(String str){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(str);
        clipboard.setContent(content);

        robot.keyPress(KeyCode.CONTROL);
        robot.keyPress(KeyCode.V);
        robot.keyRelease(KeyCode.V);
        robot.keyRelease(KeyCode.CONTROL);
    }
}
