package com.cookandroid.torchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment torchFragment, flashFragment, morseFragment, settingsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Global global = new Global();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        torchFragment = new TorchFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,torchFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.torchFragment) {
                    if (torchFragment == null) {
                        torchFragment = new TorchFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment, torchFragment).commit();
                    }
                    if (torchFragment != null)
                        getSupportFragmentManager().beginTransaction().show(torchFragment).commit();
                    if (flashFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(flashFragment).commit();
                    if (morseFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(morseFragment).commit();
                    if (settingsFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(settingsFragment).commit();
                return true;
                }
                else if(itemId == R.id.flashFragment) {
                    if (flashFragment == null) {
                        flashFragment = new FlashFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment, flashFragment).commit();
                    }
                    if (torchFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(torchFragment).commit();
                    if (flashFragment != null)
                        getSupportFragmentManager().beginTransaction().show(flashFragment).commit();
                    if (morseFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(morseFragment).commit();
                    if (settingsFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(settingsFragment).commit();

                    return true;
                }
                else if(itemId == R.id.morseFragment) {
                    if (morseFragment == null) {
                        morseFragment = new MorseFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment, morseFragment).commit();
                    }
                    if (torchFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(torchFragment).commit();
                    if (flashFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(flashFragment).commit();
                    if (morseFragment != null)
                        getSupportFragmentManager().beginTransaction().show(morseFragment).commit();
                    if (settingsFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(settingsFragment).commit();
                    return true;
                } else if(itemId == R.id.settingsFragment) {
                    if(settingsFragment == null) {
                        settingsFragment = new SettingsFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment,settingsFragment).commit();
                    }
                    if (torchFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(torchFragment).commit();
                    if (flashFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(flashFragment).commit();
                    if (morseFragment != null)
                        getSupportFragmentManager().beginTransaction().hide(morseFragment).commit();
                    if (settingsFragment != null)
                        getSupportFragmentManager().beginTransaction().show(settingsFragment).commit();
                    return true;
                }
                else
                    return false;
            }
        }); // fragment 상태 유지 https://plusratio.tistory.com/52
    }

}