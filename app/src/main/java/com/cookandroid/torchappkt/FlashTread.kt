package com.cookandroid.torchappkt

import android.os.Handler

class FlashTread(private val handler: Handler) : Runnable {
    private var flashRun = true
    private var speed = 1000

    override fun run() {
        var toggleControl = false
        while (flashRun) {
            try {
                val message = handler.obtainMessage()
                if (toggleControl) message.obj = "off"
                else message.obj = "on"
                // handler.removeCallbacksAndMessages(null);
                handler.sendMessage(message)
                Thread.sleep(speed.toLong())
                toggleControl = !(toggleControl)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }

    fun setFlashRun() {
        this.flashRun = true
    }

    fun setFlashRunStop() {
        this.flashRun = false
    }

    fun stop() {
        this.flashRun = false
    }

    fun start() {
        this.flashRun = true
    }

    fun setSpeed(speed: Int) {
        this.speed = speed
    }
}
