package com.xxxmkxxx.liquidatorsHCS;

import com.xxxmkxxx.liquidatorsHCS.files.FileController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.*;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Skript extends WorkKomb {
    static int time = 1;
    private int allTime = 0;
    static int arrCoords[][];
    private Scheduler scheduler = null;
    private List<String> listMail;
    private String pathToMail = "";
    private int indexAccaunt = 0;

    public void buildScript() {
        FileController files = new FileController();
        timeLine = new Timeline();


        listMail = files.readFileToArray(pathToMail);
        arrCoords = setCoords(Config.getField("pathToCoords"));

        for(int i = indexAccaunt; i < listMail.size(); i++) {
            startSection(Config.getField("pathToJava"), Config.getField("pathToGame"), listMail.get(i), i);
            stayAFKSection(20, 2);
            exitSection();
            if(i == listMail.size() - 1) {
                timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(Skript.time), e -> {
                    Config.setField("indexLastAccount", String.valueOf(0));
                }));
            }
        }

        allTime = time;
        time = 1;
    }

    public void runScheduler() {
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
    }

    private static int [][] setCoords(String pathToCoords){
        List <String> listCoords = new FileController().readFileToArray(pathToCoords);
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
    public int getCountAccaunt() {
        return listMail.size();
    }
    public int getAllTime() {
        return allTime;
    }

    public void setPathToMail(String pathToMail) {
        this.pathToMail = pathToMail;
    }
    public void setIndexAccaunt(int index) {
        indexAccaunt = index;
    }
}
