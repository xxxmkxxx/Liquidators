package com.xxxmkxxx.liquidatorsHCS;

import com.xxxmkxxx.liquidatorsHCS.files.Files;
import javafx.animation.Timeline;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;
import java.io.Serializable;
import java.util.*;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Skript implements Serializable {
    private Timeline timeLine = new Timeline();
    static int time = 1;
    static int arrCoords[][];
    private Scheduler scheduler = null;
    private List<String> listMail;

    public void runScript(int indexAccaunt){
        Files files = new Files();
        WorkKomb workKomb = new WorkKomb(timeLine);

        String pathToMail = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/Mail.txt";
        String pathToCoords = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/Coords.txt";
        String pathToGame = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/HCS.jar";
        String pathToJava = "C:\\Program Files\\Java\\jre1.8.0_281\\bin\\java";

        listMail = files.readFileToArray(pathToMail);
        arrCoords = setCoords(pathToCoords);

        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

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

        for(int i = indexAccaunt; i < listMail.size(); i++) {
            workKomb.startSection(pathToJava, pathToGame, listMail.get(i), i);
            workKomb.stayAFKSection(20, 2);
            workKomb.exitSection();

            if(listMail.size() - 1 == i) {
                new File("src/main/java/com/xxxmkxxx/liquidatorsHCS/files/" + "lastAccaunt.txt").delete();
            }
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
    public Scheduler getScheduler() {
        return scheduler;
    }
    public List <String> getListMail() {
        return listMail;
    }
}
