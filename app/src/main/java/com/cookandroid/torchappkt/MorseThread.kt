package com.cookandroid.torchappkt

import android.os.Handler
import android.widget.Button
import com.cookandroid.torchappkt.R

class MorseThread(private val handler: Handler, var btnMorseToggle: Button) : Runnable {
    private var morseCode = "13#311$11" // 1은 단점 3은 장점 #은 3틱 쉬기 $은 길게 쉬기
    private var tickSpeed = 100
    private var setTick = 0
    private var morseRun = true

    override fun run() {
        try {
            for (i in 0 until morseCode.length) {
                setTick = 1
                if (!morseRun) break
                val message = handler.obtainMessage()
                message.obj = "on"
                val messageOff = handler.obtainMessage()
                messageOff.obj = "off"
                if (morseCode[i] == '#' || morseCode[i] == '$') message.obj = "off"
                handler.sendMessage(message)
                if (morseCode[i] == '1') setTick = 1
                else if (morseCode[i] == '3') setTick = 3
                else if (morseCode[i] == '#') setTick = 2
                else if (morseCode[i] == '$') setTick = 6
                Thread.sleep((setTick * tickSpeed).toLong())
                handler.sendMessage(messageOff)
                Thread.sleep(tickSpeed.toLong())
            }
            handler.post { btnMorseToggle.setText(R.string.btnMoresPlay) }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    fun stop() {
        this.morseRun = false
    }

    fun start() {
        this.morseRun = true
    }

    fun setMorseCode(morseCode: String) {
        this.morseCode = morseCode
    }

    fun setTickSpeed(tickSpeed: Int) {
        this.tickSpeed = tickSpeed
    }
}


