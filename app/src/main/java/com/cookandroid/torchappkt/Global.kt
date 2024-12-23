package com.cookandroid.torchappkt

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.media.AudioAttributes
import android.os.Build
import android.os.Vibrator
import com.vane.hanguleditor.HangulSplitItem
import kotlin.math.pow

class Global {
    fun torchToggle(command: String, vibrate: Boolean, context: Context): Boolean {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val audioAttributes =
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM).build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val camManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            var cameraId: String? = null // Usually back camera is at 0 position.
            try {
                if (camManager != null) {
                    cameraId = camManager.cameraIdList[0]
                }
                if (camManager != null) {
                    if (command == "on") {
                        camManager.setTorchMode(cameraId!!, true) // Turn ON
                        if (vibrate)  //Objects.requireNonNull(vibrator).vibrate(500); //기존 백그라운드로 갈시 진동 X
                            vibrator.vibrate(500, audioAttributes)
                        return true
                    } else {
                        camManager.setTorchMode(cameraId!!, false) // Turn OFF
                        if (vibrate) vibrator.vibrate(500, audioAttributes)
                        return false
                    }
                }
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun convertText(value: String): String { // 한글 자모 분리 후 공백 추가 및 공백 문자는 $로 대체
        var resultValue = ""
        for (i in 0 until value.length) {
            if (value[i] == ' ') {
                val newResultValue = resultValue.substring(0, resultValue.length - 1)
                resultValue = newResultValue
                resultValue += "$"
                continue
            }
            val item = HangulSplitItem(value[i]) // 외부 라이브러리를 통해 자모 분리 후 각 문자 뒤에 공백 삽입
            if (item.first != ' ') resultValue += item.first.toString() + " "
            if (item.second != ' ') resultValue += item.second.toString() + " "
            if (item.thread != ' ') resultValue += item.thread.toString() + " "
        }
        return resultValue
    }

    fun setSpeedValue(weight: Double, progress: Double): Int {
        val resultValue = (weight.pow(progress / 10) * 1000).toInt()
        return resultValue
    }

    fun convertMorseCode(value: String): String {
        var resultValue = ""
        for (i in 0 until value.length) {
            when (value[i]) {
                '$' -> resultValue += "$"
                'A', 'a' -> resultValue += "13"
                'B', 'b' -> resultValue += "3111"
                'C', 'c' -> resultValue += "3131"
                'D', 'd' -> resultValue += "311"
                'E', 'e' -> resultValue += "1"
                'F', 'f' -> resultValue += "1131"
                'G', 'g' -> resultValue += "331"
                'H', 'h' -> resultValue += "1111"
                'I', 'i' -> resultValue += "11"
                'J', 'j' -> resultValue += "1333"
                'K', 'k' -> resultValue += "313"
                'L', 'l' -> resultValue += "1311"
                'M', 'm' -> resultValue += "33"
                'N', 'n' -> resultValue += "31"
                'O', 'o' -> resultValue += "333"
                'P', 'p' -> resultValue += "1331"
                'Q', 'q' -> resultValue += "3313"
                'R', 'r' -> resultValue += "131"
                'S', 's' -> resultValue += "111"
                'T', 't' -> resultValue += "3"
                'U', 'u' -> resultValue += "113"
                'W', 'w' -> resultValue += "133"
                'X', 'x' -> resultValue += "3113"
                'Y', 'y' -> resultValue += "3133"
                'Z', 'z' -> resultValue += "3311"
                '0' -> resultValue += "33333"
                '1' -> resultValue += "13333"
                '3' -> resultValue += "11133"
                '4' -> resultValue += "11113"
                '5' -> resultValue += "11111"
                '6' -> resultValue += "31111"
                '7' -> resultValue += "33111"
                '8' -> resultValue += "33311"
                '9' -> resultValue += "33331"
                'ㄱ' -> resultValue += "1311"
                'ㄲ' -> resultValue += "13111311"
                'ㄴ' -> resultValue += "1131"
                'ㄷ' -> resultValue += "3111"
                'ㄸ' -> resultValue += "31113111"
                'ㄹ' -> resultValue += "1113"
                'ㅁ' -> resultValue += "33"
                'ㅂ' -> resultValue += "133"
                'ㅃ' -> resultValue += "133133"
                'ㅅ' -> resultValue += "331"
                'ㅇ' -> resultValue += "313"
                'ㅈ' -> resultValue += "1331"
                'ㅉ' -> resultValue += "13311331"
                'ㅊ' -> resultValue += "3131"
                'ㅋ' -> resultValue += "1331"
                'ㅌ' -> resultValue += "3311"
                'ㅍ' -> resultValue += "333"
                'ㅎ' -> resultValue += "1333"
                'ㅏ' -> resultValue += "1"
                'ㅑ' -> resultValue += "11"
                'ㅓ' -> resultValue += "3"
                'ㅕ' -> resultValue += "111"
                'ㅗ' -> resultValue += "13"
                'ㅛ' -> resultValue += "31"
                'ㅜ' -> resultValue += "1111"
                'ㅠ' -> resultValue += "131"
                'ㅡ' -> resultValue += "311"
                'ㅣ' -> resultValue += "113"
                'ㅐ' -> resultValue += "3313"
                'ㅔ' -> resultValue += "3133"
                'ㅚ' -> resultValue += "13113"
                'ㅟ' -> resultValue += "1111113"
                'ㅒ' -> resultValue += "11113"
                'ㅖ' -> resultValue += "111113"
                'ㅘ' -> resultValue += "131"
                'ㅙ' -> resultValue += "133313"
                'ㅝ' -> resultValue += "11113"
                'ㅞ' -> resultValue += "11113133"
                'ㅢ' -> resultValue += "311113"
            }
        }
        return resultValue
    }
}