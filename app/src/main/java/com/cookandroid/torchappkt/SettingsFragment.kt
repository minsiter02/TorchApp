package com.cookandroid.torchappkt

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.cookandroid.torchappkt.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}