package com.casino.uri.androidpokedex;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class SecretActivityFragment extends Fragment
{
    private String DEOXYS_URL = "https://i.ytimg.com/vi/-WUoyAzHh50/maxresdefault.jpg";
    public SecretActivityFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View secretActivityFragment = inflater.inflate(R.layout.fragment_secret, container, false);
        TextView secretText = (TextView) secretActivityFragment.findViewById(R.id.TVsecret);
        ImageView deoxys = (ImageView) secretActivityFragment.findViewById(R.id.IVsecret);

        secretText.setText("");
        Picasso.with(getContext()).load(DEOXYS_URL).fit().into(deoxys);

        return secretActivityFragment;
    }
}
