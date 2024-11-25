package com.cookandroid.torchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    Fragment torchFragment, flashFragment, morseFragment, userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        torchFragment = new TorchFragment();
        flashFragment = new FlashFragment();
        morseFragment = new MorseFragment();
        userFragment = new UserFragment();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Fragment fragment = null;
                if(itemId == R.id.torchFragment) {
                    fragment = torchFragment;
                } else if(itemId == R.id.flashFragment) {
                    fragment = flashFragment;
                } else if(itemId == R.id.morseFragment) {
                    fragment = morseFragment;
                } else if(itemId == R.id.userFragment) {
                    fragment = userFragment;
                }
                return loadFragment(fragment);
            }
        });

    }
    boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
            return true;
        }else{
            return false;
        }
    }
}