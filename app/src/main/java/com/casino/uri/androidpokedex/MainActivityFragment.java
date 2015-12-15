package com.casino.uri.androidpokedex;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import com.squareup.picasso.Picasso;

public class MainActivityFragment extends Fragment
{
    private String TITLE_URI = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/English_Pok%C3%A9mon_logo.svg/2000px-English_Pok%C3%A9mon_logo.svg.png";
    private String MASTERBALL_URI = "http://orig13.deviantart.net/41d7/f/2014/103/4/8/master_ball__01__by_adfpf1-d7ea28n.png";
    VideoView mainVideo;
    ImageView title;
    ImageView masterBall;


    @Override
    public void onStart()
    {
        super.onStart();
        mainVideo.requestFocus();
        mainVideo.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mainActivityFragment = inflater.inflate(R.layout.fragment_main, container, false);

        mainVideo = (VideoView) mainActivityFragment.findViewById(R.id.VVmainVideo);
        title = (ImageView) mainActivityFragment.findViewById(R.id.IVtitle);
        masterBall = (ImageView) mainActivityFragment.findViewById(R.id.IVmasterBall);

        mainVideo.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.intro));
        mainVideo.setMediaController(new MediaController(getContext()));

        masterBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent secret = new Intent(getContext(), SecretActivity.class);
                startActivity(secret);
            }
        });

        Picasso.with(getContext()).load(TITLE_URI).fit().into(title);
        Picasso.with(getContext()).load(MASTERBALL_URI).fit().into(masterBall);

        return mainActivityFragment;
    }
    public MainActivityFragment() {}
}
