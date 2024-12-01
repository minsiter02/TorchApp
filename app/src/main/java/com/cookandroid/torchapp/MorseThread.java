package com.cookandroid.torchapp;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MorseThread  implements Runnable{
    private Handler handler;
    private String morseCode = "13#311$11";// 1은 단점 3은 장점 #은 3틱 쉬기 $은 길게 쉬기
    private int tickSpeed = 100;
    private int setTick;
    Button btnMorseToggle;
    private boolean morseRun = true;

    public MorseThread(Handler handler, Button btnMorseToggle){
        this.handler = handler;
        this.btnMorseToggle = btnMorseToggle;

    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < morseCode.length(); i++){
                setTick = 1;
                if(!morseRun)
                    break;
                Message message = handler.obtainMessage();
                message.obj = "on";
                Message messageOff = handler.obtainMessage();
                messageOff.obj = "off";
                if(morseCode.charAt(i) == '#'|| morseCode.charAt(i) == '$')
                    message.obj = "off";
                handler.sendMessage(message);
                if(morseCode.charAt(i) == '1')
                    setTick = 1;
                else if(morseCode.charAt(i) == '3')
                    setTick = 3;
                else if(morseCode.charAt(i) == '#')
                    setTick = 2;
                else if (morseCode.charAt(i) == '$')
                    setTick = 6;
                Thread.sleep(setTick*tickSpeed);
                handler.sendMessage(messageOff);
                Thread.sleep(tickSpeed);
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    btnMorseToggle.setText(R.string.btnMoresPlay);
                }
            });

        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
    }

    }
    public void stop() {
        this.morseRun = false;
    }
    public void start() {
        this.morseRun = true;
    }
    public void setMorseCode(String morseCode){
        this.morseCode = morseCode;
    }

    public void setTickSpeed(int tickSpeed){
        this.tickSpeed = tickSpeed;
    }

}


