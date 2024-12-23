package com.cookandroid.torchappkt

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cookandroid.torchappkt.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private var torchFragment: Fragment? = null
    private var flashFragment: Fragment? = null
    private var morseFragment: Fragment? = null
    private var settingsFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val global = Global()
        val bottomNavigationView: BottomNavigationView? = findViewById(R.id.bottomNavigationView)
        bottomNavigationView?.setBackgroundColor(Color.DKGRAY)
        torchFragment = TorchFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment,
            torchFragment as TorchFragment
        ).commit()

        bottomNavigationView?.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            val itemId = item.itemId
            if (itemId == R.id.torchFragment) {
                if (torchFragment == null) {
                    torchFragment = TorchFragment()
                    supportFragmentManager.beginTransaction().add(
                        R.id.fragment,
                        torchFragment as TorchFragment
                    )
                        .commit()
                }
                if (torchFragment != null) supportFragmentManager.beginTransaction()
                    .show(torchFragment as TorchFragment).commit()
                if (flashFragment != null) supportFragmentManager.beginTransaction().hide(
                    flashFragment!!
                ).commit()
                if (morseFragment != null) supportFragmentManager.beginTransaction().hide(
                    morseFragment!!
                ).commit()
                if (settingsFragment != null) supportFragmentManager.beginTransaction().hide(
                    settingsFragment!!
                ).commit()
                true
            } else if (itemId == R.id.flashFragment) {
                if (flashFragment == null) {
                    flashFragment = FlashFragment()
                    supportFragmentManager.beginTransaction().add(
                        R.id.fragment,
                        flashFragment as FlashFragment
                    )
                        .commit()
                }
                if (torchFragment != null) supportFragmentManager.beginTransaction()
                    .hide(torchFragment as TorchFragment).commit()
                if (flashFragment != null) supportFragmentManager.beginTransaction().show(
                    flashFragment!!
                ).commit()
                if (morseFragment != null) supportFragmentManager.beginTransaction().hide(
                    morseFragment!!
                ).commit()
                if (settingsFragment != null) supportFragmentManager.beginTransaction().hide(
                    settingsFragment!!
                ).commit()

                true
            } else if (itemId == R.id.morseFragment) {
                if (morseFragment == null) {
                    morseFragment = MorseFragment()
                    supportFragmentManager.beginTransaction().add(
                        R.id.fragment,
                        morseFragment as MorseFragment
                    )
                        .commit()
                }
                if (torchFragment != null) supportFragmentManager.beginTransaction()
                    .hide(torchFragment as TorchFragment).commit()
                if (flashFragment != null) supportFragmentManager.beginTransaction().hide(
                    flashFragment!!
                ).commit()
                if (morseFragment != null) supportFragmentManager.beginTransaction().show(
                    morseFragment!!
                ).commit()
                if (settingsFragment != null) supportFragmentManager.beginTransaction().hide(
                    settingsFragment!!
                ).commit()
                true
            } else if (itemId == R.id.settingsFragment) {
                if (settingsFragment == null) {
                    settingsFragment = SettingsFragment()
                    supportFragmentManager.beginTransaction().add(
                        R.id.fragment,
                        settingsFragment as SettingsFragment
                    )
                        .commit()
                }
                if (torchFragment != null) supportFragmentManager.beginTransaction()
                    .hide(torchFragment as TorchFragment).commit()
                if (flashFragment != null) supportFragmentManager.beginTransaction().hide(
                    flashFragment!!
                ).commit()
                if (morseFragment != null) supportFragmentManager.beginTransaction().hide(
                    morseFragment!!
                ).commit()
                if (settingsFragment != null) supportFragmentManager.beginTransaction().show(
                    settingsFragment!!
                ).commit()
                true
            } else false
        }) // fragment 상태 유지 https://plusratio.tistory.com/52
    }
}