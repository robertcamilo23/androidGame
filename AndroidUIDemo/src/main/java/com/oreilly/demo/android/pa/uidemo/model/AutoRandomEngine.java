package com.oreilly.demo.android.pa.uidemo.model;

import java.util.Random;

/**
 * Created by Hamdan on 4/18/14.
 */
public class AutoRandomEngine implements Runnable {
    private Runnable task;
    private Boolean stopped = false;
    private int minTime;
    private int maxTime;
    private int variableTime;
    Random random = new Random();

    public AutoRandomEngine (Runnable task, int minTime, int maxTime){
        this.task = task;
        this .minTime = minTime;
        this .maxTime = maxTime;

   }
    @Override
    public void run() {
        while (!stopped) {
            variableTime = minTime + (random.nextInt(maxTime - minTime));
            task.run();
            try {
                Thread.sleep(variableTime);
            } catch (InterruptedException e) {
            }
        }
    }
    public void cancel(){
        stopped = true;
    }

}
