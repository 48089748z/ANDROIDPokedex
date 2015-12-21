package com.casino.uri.androidpokedex;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivityFragment extends PreferenceFragment
{
    SharedPreferences myPreferences;
    Switch musicSwitch;
    Switch soundsSwitch;
    SharedPreferences.Editor sharedPreferencesEditor;

    public SettingsActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View settingsActivity = inflater.inflate(R.layout.fragment_settings, container, false);

        myPreferences = getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        musicSwitch = (Switch) settingsActivity.findViewById(R.id.SWmusic);
        soundsSwitch = (Switch) settingsActivity.findViewById(R.id.SWsounds);

        musicSwitch.setChecked(myPreferences.getBoolean("musicON", true));
        soundsSwitch.setChecked(myPreferences.getBoolean("soundsON", true));
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    sharedPreferencesEditor = myPreferences.edit();
                    sharedPreferencesEditor.putBoolean("musicON", true);
                    sharedPreferencesEditor.commit();
                }
                else
                {
                    sharedPreferencesEditor = myPreferences.edit();
                    sharedPreferencesEditor.putBoolean("musicON", false);
                    sharedPreferencesEditor.commit();
                }
            }
        });

        soundsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    sharedPreferencesEditor = myPreferences.edit();
                    sharedPreferencesEditor.putBoolean("soundsON", true);
                    sharedPreferencesEditor.commit();
                }
                else
                {
                    sharedPreferencesEditor = myPreferences.edit();
                    sharedPreferencesEditor.putBoolean("soundsON", false);
                    sharedPreferencesEditor.commit();
                }
            }
        });
        return settingsActivity;
    }
}
