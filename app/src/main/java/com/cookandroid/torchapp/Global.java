package com.cookandroid.torchapp;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Vibrator;

import java.util.Objects;

public class Global {
    public boolean torchToggle(String command, Context context) {

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager camManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null; // Usually back camera is at 0 position.
            try {
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0];
                }
                if (camManager != null) {
                    if (command.equals("on")) {
                        camManager.setTorchMode(cameraId, true);   // Turn ON
                        Objects.requireNonNull(vibrator).vibrate(500);
                        return true;

                    } else {
                        camManager.setTorchMode(cameraId, false);  // Turn OFF
                        Objects.requireNonNull(vibrator).vibrate(500);
                        return false;
                    }
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}