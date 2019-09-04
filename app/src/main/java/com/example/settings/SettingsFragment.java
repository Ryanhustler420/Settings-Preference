package com.example.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {

    public static final String PREF_CONNECTION_TYPE = "connection_type";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(PREF_CONNECTION_TYPE)) {
                    Preference connection_pref = findPreference(key);
                    connection_pref.setSummary(sharedPreferences.getString(key, ""));
                }

                // we can handle more cases here...
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        Preference connectionTypePref = findPreference(PREF_CONNECTION_TYPE);
        connectionTypePref.setSummary(getPreferenceScreen().getSharedPreferences().getString(PREF_CONNECTION_TYPE, ""));

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }
}
