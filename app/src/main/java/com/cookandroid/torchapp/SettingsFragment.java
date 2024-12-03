package com.cookandroid.torchapp;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}