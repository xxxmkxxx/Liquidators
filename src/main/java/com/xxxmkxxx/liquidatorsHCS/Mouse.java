package com.xxxmkxxx.liquidatorsHCS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseButton;
import javafx.scene.robot.Robot;
import javafx.util.Duration;

public class Mouse {
    private Robot robot = new Robot();

    //Метод для перемещения курсора на координатs
    public void moveXY(int x, int y){
        Timeline timeLine = new Timeline(
                new KeyFrame(Duration.ZERO, e -> robot.mouseMove(x, y)),
                new KeyFrame(Duration.seconds(1))
        );
        timeLine.play();
    }

    //Метод для клика мыши
    public void clickButton(){
        Timeline timeLine = new Timeline(
                new KeyFrame(Duration.ZERO, e -> robot.mousePress(MouseButton.PRIMARY)),
                new KeyFrame(Duration.millis(10), e -> robot.mouseRelease(MouseButton.PRIMARY))
        );
        timeLine.play();
    }
}
