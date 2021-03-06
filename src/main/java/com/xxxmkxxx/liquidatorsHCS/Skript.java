package com.xxxmkxxx.liquidatorsHCS;

import com.xxxmkxxx.liquidatorsHCS.files.Files;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Skript implements Serializable {
    private Timeline timeLine = new Timeline();
    static int time = 1;
    static int arrCoords[][];

    public void runScript(){
        Files files = new Files();
        WorkKomb workKomb = new WorkKomb(timeLine);

        String pathToMail = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/Mail.txt";
        String pathToCoords = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/Coords.txt";
        String pathToGame = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/HCS.jar";
        String pathToJava = "\"C:\\Program Files\\Java\\jre1.8.0_281\\bin\\java\" -jar";

        List<String> listMail = files.readFileToArray(pathToMail);
        Queue<String> queueMail = listToQueue(listMail);
        arrCoords = setCoords(pathToCoords);

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail job = newJob(WorkKomb.class)
                    .withIdentity("restartSection", "restart")
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("timeTrigger", "restart")
                    .startNow()
                    .withSchedule(dailyAtHourAndMinute(4, 58))
                    .build();

            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        Iterator iterator = queueMail.iterator();
        while (iterator.hasNext()) {
            workKomb.startSection(pathToJava, pathToGame, queueMail.peek());
            queueMail.poll();
            workKomb.stayAFKSection(20, 2);
            workKomb.exitSection();
        }

        timeLine.play();
    }

    private static int [][] setCoords(String pathToCoords){
        List <String> listCoords = new Files().readFileToArray(pathToCoords);
        int arrCoords[][] = new int[listCoords.size()][2];

        for(int i = 0; i < listCoords.size(); i++){
            arrCoords[i][0] = Integer.parseInt(listCoords.get(i).split(" ")[0]);
            arrCoords[i][1] = Integer.parseInt(listCoords.get(i).split(" ")[1]);
        }

        return arrCoords;
    }

    public Timeline getTimeLine() {
        return timeLine;
    }

    private static Queue listToQueue(List list){
        Queue queueTemp = new LinkedList();

        for (Object o : list) {
            queueTemp.add(o);
        }
        return queueTemp;
    }
}
