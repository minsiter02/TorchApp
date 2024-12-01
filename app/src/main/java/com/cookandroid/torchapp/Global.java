package com.cookandroid.torchapp;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Vibrator;

import com.vane.hanguleditor.HangulSplitItem;

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
    public String convertText(String value) { // 한글 자모 분리 후 공백 추가 및 공백 문자는 $로 대체
        String resultValue ="";
        for(int i = 0; i< value.length(); i++) {
            if( value.charAt(i)==' '){
                String newResultValue = resultValue.substring(0, resultValue.length() -1);
                resultValue = newResultValue;
                resultValue += "$";
                continue;
            }
            HangulSplitItem item = new HangulSplitItem(value.charAt(i)); // 외부 라이브러리를 통해 자모 분리 후 각 문자 뒤에 공백 삽입
            if (item.getFirst()!=' ')
                resultValue += Character.toString(item.getFirst()) + " ";
            if (item.getSecond()!=' ')
                resultValue += Character.toString(item.getSecond())+ " ";
            if (item.getThread()!=' ')
                resultValue += Character.toString(item.getThread()) + " ";
        }
        return resultValue;
    }
    public String convertMorseCode(String value) {
        String resultValue = "";
        for ( int i = 0; i < value.length(); i++ ){
            switch (value.charAt(i)){
                case '$': // 공백 문자
                    resultValue +="$"; break;
                // 영어
                case 'A':
                case 'a':
                    resultValue +="13"; break;
                case 'B':
                case 'b':
                    resultValue +="3111"; break;
                case 'C':
                case 'c':
                    resultValue +="3131"; break;
                case 'D':
                case 'd':
                    resultValue +="311"; break;
                case 'E':
                case 'e':
                    resultValue +="1"; break;
                case 'F':
                case 'f':
                    resultValue +="1131"; break;
                case 'G':
                case 'g':
                    resultValue +="331"; break;
                case 'H':
                case 'h':
                    resultValue +="1111"; break;
                case 'I':
                case 'i':
                    resultValue +="11"; break;
                case 'J':
                case 'j':
                    resultValue +="1333"; break;
                case 'K':
                case 'k':
                    resultValue +="313"; break;
                case 'L':
                case 'l':
                    resultValue +="1311"; break;
                case 'M':
                case 'm':
                    resultValue +="33"; break;
                case 'N':
                case 'n':
                    resultValue +="31"; break;
                case 'O':
                case 'o':
                    resultValue +="333"; break;
                case 'P':
                case 'p':
                    resultValue +="1331"; break;
                case 'Q':
                case 'q':
                    resultValue +="3313"; break;
                case 'R':
                case 'r':
                    resultValue +="131"; break;
                case 'S':
                case 's':
                    resultValue +="111"; break;
                case 'T':
                case 't':
                    resultValue +="3"; break;
                case 'U':
                case 'u':
                    resultValue +="113"; break;
                case 'W':
                case 'w':
                    resultValue +="133"; break;
                case 'X':
                case 'x':
                    resultValue +="3113"; break;
                case 'Y':
                case 'y':
                    resultValue +="3133"; break;
                case 'Z':
                case 'z':
                    resultValue +="3311"; break;
                // 숫자
                 case '0':
                    resultValue +="33333"; break;
                case '1':
                    resultValue +="13333"; break;
                case '3':
                    resultValue +="11133"; break;
                case '4':
                    resultValue +="11113"; break;
                case '5':
                    resultValue +="11111"; break;
                case '6':
                    resultValue +="31111"; break;
                case '7':
                    resultValue +="33111"; break;
                case '8':
                    resultValue +="33311"; break;
                case '9':
                    resultValue +="33331"; break;
                //한글 자음
                case 'ㄱ':
                    resultValue +="1311"; break;
                case 'ㄲ':
                    resultValue +="13111311"; break;
                case 'ㄴ':
                    resultValue +="1131"; break;
                case 'ㄷ':
                    resultValue +="3111"; break;
                case 'ㄸ':
                    resultValue +="31113111"; break;
                case 'ㄹ':
                    resultValue +="1113"; break;
                case 'ㅁ':
                    resultValue +="33"; break;
                case 'ㅂ':
                    resultValue +="133"; break;
                case 'ㅃ':
                    resultValue +="133133"; break;
                case 'ㅅ':
                    resultValue +="331"; break;
                case 'ㅇ':
                    resultValue +="313"; break;
                case 'ㅈ':
                    resultValue +="1331"; break;
                case 'ㅉ':
                    resultValue +="13311331"; break;
                case 'ㅊ':
                    resultValue +="3131"; break;
                case 'ㅋ':
                    resultValue +="1331"; break;
                case 'ㅌ':
                    resultValue +="3311"; break;
                case 'ㅍ':
                    resultValue +="333"; break;
                case 'ㅎ':
                    resultValue +="1333"; break;
                //한글 모음
                case 'ㅏ':
                    resultValue +="1"; break;
                case 'ㅑ':
                    resultValue +="11"; break;
                case 'ㅓ':
                    resultValue +="3"; break;
                case 'ㅕ':
                    resultValue +="111"; break;
                case 'ㅗ':
                    resultValue +="13"; break;
                case 'ㅛ':
                    resultValue +="31"; break;
                case 'ㅜ':
                    resultValue +="1111"; break;
                case 'ㅠ':
                    resultValue +="131"; break;
                case 'ㅡ':
                    resultValue +="311"; break;
                case 'ㅣ':
                    resultValue +="113"; break;
                case 'ㅐ':
                    resultValue +="3313"; break;
                case 'ㅔ':
                    resultValue +="3133"; break;
                case 'ㅚ':
                    resultValue +="13113"; break;
                case 'ㅟ':
                    resultValue +="1111113"; break;
                case 'ㅒ':
                    resultValue +="11113"; break;
                case 'ㅖ':
                    resultValue +="111113"; break;
                case 'ㅘ':
                    resultValue +="131"; break;
                case 'ㅙ':
                    resultValue +="133313"; break;
                case 'ㅝ':
                    resultValue +="11113"; break;
                case 'ㅞ':
                    resultValue +="11113133"; break;
                case 'ㅢ':
                    resultValue +="311113"; break;

            }
        }
        return resultValue;
    }
}