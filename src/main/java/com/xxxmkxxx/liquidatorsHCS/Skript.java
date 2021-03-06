package com.xxxmkxxx.liquidatorsHCS;

import com.xxxmkxxx.liquidatorsHCS.files.Files;
import javafx.animation.Timeline;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Skript implements Serializable {
    private Timeline timeLine = new Timeline();
    static int time;
    static int arrCoords[][];

    public void runScript(){
        Files files = new Files();
        WorkKomb workKomb = new WorkKomb(timeLine);

        String pathToMail = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/Mail.txt";
        String pathToCoords = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/Coords.txt";
        String pathToGame = "src/main/java/com/xxxmkxxx/liquidatorsHCS/config/HCS.exe";

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


        AtomicBoolean onFineshed = new AtomicBoolean(true);
        Iterator iterator = queueMail.iterator();
        while(iterator.hasNext() && onFineshed.get()) {
            onFineshed.set(false);
            time = 0;

            workKomb.startSection(pathToGame, queueMail.peek());
            iterator.remove();
            workKomb.stayAFKSection(20, 2);
            workKomb.exitSection();

            timeLine.play();

            timeLine.setOnFinished(e -> {
                onFineshed.set(true);
                timeLine = new Timeline();
            });
       }
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
