package com.cookandroid.torchapp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class FlashTread implements Runnable {
    private Handler handler;
    private boolean flashRun = true;
    private int speed = 1000;
    FlashFragment flashFragment = new FlashFragment();

    public FlashTread(Handler handler){
        this.handler = handler;
    }
    @Override
    public void run()  {
        boolean toggleControl = false;
        while (flashRun) {
            try {
                Message message = handler.obtainMessage();
                if (toggleControl)
                    message.obj = "off";
                else
                    message.obj = "on";
                // handler.removeCallbacksAndMessages(null);
                handler.sendMessage(message);
                Thread.sleep(speed);
                toggleControl = !(toggleControl);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Runnable interrupted");
            }

        }

    }
    public void setFlashRun(){
        this.flashRun = true;
    }
    public void setFlashRunStop(){ this.flashRun = false; }

    public void stop() {
        this.flashRun = false;
    }
    public void start() {
        this.flashRun = true;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
}
