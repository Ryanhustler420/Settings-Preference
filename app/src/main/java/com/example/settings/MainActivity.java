package com.example.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button settings, readSettings;
    TextView connectionTypeText;

    /*
     * settings pages preferences keys
     * */

    public static final String PREF_DELETE_OLD_MESSAGE = "pref_delete_old_message";
    public static final String PREF_DELIVERY_REPORTS_SMS = "pref_delivery_reports_sms";
    public static final String PREF_DELIVERY_REPORTS_MMS = "pref_delivery_reports_mms";
    public static final String PREF_VIBRATE_WHEN_RING = "pref_vibrate_when_ring";
    public static final String PREF_VOICE_MAIL = "pref_voice_mail";
    public static final String CONNECTION_TYPE = "connection_type";
    public static final String PREF_SYNC = "pref_sync";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = findViewById(R.id.settings);
        readSettings = findViewById(R.id.readValue);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        connectionTypeText = findViewById(R.id.connection_type_text);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        readSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readSettings(view);
            }
        });
    }

    public void readSettings(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        connectionTypeText.setText(String.format("Connection Type: %s", String.valueOf(sharedPreferences.getString(CONNECTION_TYPE, ""))));
    }
}
