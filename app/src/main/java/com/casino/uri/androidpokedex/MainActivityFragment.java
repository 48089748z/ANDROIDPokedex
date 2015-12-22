package com.casino.uri.androidpokedex;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.casino.uri.androidpokedex.com.pojos.Pokedex;
import com.casino.uri.androidpokedex.com.pojos.Pokemon;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonContentValues;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.GregorianCalendar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public class MainActivityFragment extends Fragment
{
    Integer pokedexSize = 720;
    MediaPlayer sounds;
    SharedPreferences myPreferences;
    private String TITLE_URI = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/English_Pok%C3%A9mon_logo.svg/2000px-English_Pok%C3%A9mon_logo.svg.png";
    private String MASTERBALL_URI = "http://orig13.deviantart.net/41d7/f/2014/103/4/8/master_ball__01__by_adfpf1-d7ea28n.png";
    private String BASE_URL = "http://pokeapi.co/api/v1/";
    VideoView mainVideo;
    ImageView title;
    ImageView masterBall;
    PokemonService pokemonService;
    Retrofit retrofit;

    public class AlarmReciever extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            deleteDatabase();
            createRetrofit();
            getPokedexSize();
            downloadPokemons();
        }
    }
    public void setAlarm()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23); // Repeats the alarm everyday at 23:00 hours
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        PendingIntent pi = PendingIntent.getService(getContext(), 0,
                new Intent(getContext(), AlarmReciever.class),PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pi);
    }

    public MainActivityFragment() {}
    @Override
    public void onStart()
    {
        super.onStart();
        myPreferences = getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        mainVideo.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mainActivityFragment = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        setAlarm();

        myPreferences = getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        mainVideo = (VideoView) mainActivityFragment.findViewById(R.id.VVmainVideo);
        title = (ImageView) mainActivityFragment.findViewById(R.id.IVtitle);
        masterBall = (ImageView) mainActivityFragment.findViewById(R.id.IVmasterBall);

        mainVideo.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.video_intro));
        mainVideo.setMediaController(new MediaController(getContext()));
        mainVideo.requestFocus();

        Picasso.with(getContext()).load(TITLE_URI).fit().into(title);
        Picasso.with(getContext()).load(MASTERBALL_URI).fit().into(masterBall);
        mainVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                mainVideo.requestFocus();
                mainVideo.start();
            }
        });

        return mainActivityFragment;
    }
    public void deleteDatabase()
    {
        getContext().getContentResolver().delete(
                PokemonColumns.CONTENT_URI,
                null,
                null);
    }
    public void downloadPokemons()
    {
        for (int id=1; id<=pokedexSize; id++)
        {
            Call<Pokemon> call = pokemonService.getPokemon(id);
            call.enqueue(new Callback<Pokemon>()
            {
                @Override
                public void onResponse(Response<Pokemon> response, Retrofit retrofit)
                {
                    if (response.isSuccess())
                    {
                        Pokemon pokemon = response.body();
                        PokemonContentValues values = new PokemonContentValues();
                        values.putPkdxId(pokemon.getPkdxId().toString());
                        values.putName(pokemon.getName());
                        values.putSpatk(pokemon.getSpAtk().toString());
                        values.putSpdef(pokemon.getSpDef().toString());
                        values.putWeight(pokemon.getWeight());
                        values.putHp(pokemon.getHp().toString());
                        values.putCreated(pokemon.getCreated());
                        values.putModified(pokemon.getModified());
                        values.putImage("http://pokeapi.co/media/img/" + pokemon.getPkdxId().toString() + ".png");
                        String types ="";
                        for (int x=0; x<pokemon.getTypes().size(); x++)
                        {
                            types = types +" - "+ pokemon.getTypes().get(x).getName();
                        }
                        values.putTypes(types);
                        getContext().getContentResolver().insert(PokemonColumns.CONTENT_URI, values.values());
                    }
                }
                @Override
                public void onFailure(Throwable t) {}
            });
        }
    }
    public interface PokemonService
    {
        @GET("pokemon/{id}")
        Call<Pokemon>getPokemon(
                @Path("id") Integer id);

        @GET("pokedex/1/")
        Call<Pokedex>getPokedex();
    }
    public void createRetrofit()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokemonService = retrofit.create(PokemonService.class);
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            Intent settingsActivity = new Intent(getContext(), SettingsActivity.class);
            startActivity(settingsActivity);
        }
        if (id == R.id.action_refresh)
        {
            warningWindow();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void warningWindow()
    {
        new AlertDialog.Builder(getContext())
                .setTitle("        WARNING")
                .setMessage("Are you sure you want to delete the whole Pokedex Database to fully download it again from the internet?\n\nThis is only recommended if you have Good Wifi Connection")
                .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        sounds = MediaPlayer.create(getContext(), R.raw.sound_download);
                        sounds.start();
                        deleteDatabase();
                        createRetrofit();
                        getPokedexSize();
                        downloadPokemons();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){}
                })
                .setIcon(R.drawable.ic_alert_48)
                .show();
    }
    public void getPokedexSize() {
        Call<Pokedex> call = pokemonService.getPokedex();
        call.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Response<Pokedex> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Pokedex pokedex = response.body();
                    pokedexSize = pokedex.getPokemon().size();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                    pokedexSize = 0;
                }
            });
    }
}
