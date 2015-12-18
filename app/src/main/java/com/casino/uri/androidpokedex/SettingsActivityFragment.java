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
    Switch music;
    Switch sounds;
    SharedPreferences.Editor editor;

    public SettingsActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View settingsActivity = inflater.inflate(R.layout.fragment_settings, container, false);

        myPreferences = getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        music = (Switch) settingsActivity.findViewById(R.id.SWmusic);
        sounds = (Switch) settingsActivity.findViewById(R.id.SWsounds);

        music.setChecked(myPreferences.getBoolean("musicON", true));
        sounds.setChecked(myPreferences.getBoolean("soundsON", true));
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    editor = myPreferences.edit();
                    editor.putBoolean("musicON", true);
                    editor.commit();
                }
                else
                {
                    editor = myPreferences.edit();
                    editor.putBoolean("musicON", false);
                    editor.commit();
                }

            }
        });
        sounds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    editor = myPreferences.edit();
                    editor.putBoolean("soundsON", true);
                    editor.commit();
                }
                else
                {
                    editor = myPreferences.edit();
                    editor.putBoolean("soundsON", false);
                    editor.commit();
                }
            }
        });
        return settingsActivity;
    }
}
