package com.casino.uri.androidpokedex;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.casino.uri.androidpokedex.com.pojos.Pokemon;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonContentValues;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public class MainActivityFragment extends Fragment
{
    private String TITLE_URI = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/English_Pok%C3%A9mon_logo.svg/2000px-English_Pok%C3%A9mon_logo.svg.png";
    private String MASTERBALL_URI = "http://orig13.deviantart.net/41d7/f/2014/103/4/8/master_ball__01__by_adfpf1-d7ea28n.png";
    private String BASE_URL = "http://pokeapi.co/api/v1/";
    VideoView mainVideo;
    ImageView title;
    ImageView masterBall;
    PokemonService service;
    Retrofit retrofit;

    public MainActivityFragment() {}
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
        setHasOptionsMenu(true);

        mainVideo = (VideoView) mainActivityFragment.findViewById(R.id.VVmainVideo);
        title = (ImageView) mainActivityFragment.findViewById(R.id.IVtitle);
        masterBall = (ImageView) mainActivityFragment.findViewById(R.id.IVmasterBall);

        mainVideo.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.intro));
        mainVideo.setMediaController(new MediaController(getContext()));

        Picasso.with(getContext()).load(TITLE_URI).fit().into(title);
        Picasso.with(getContext()).load(MASTERBALL_URI).fit().into(masterBall);

        return mainActivityFragment;
    }
    public void deleteDatabase()
    {
        getContext().getContentResolver().delete(
                PokemonColumns.CONTENT_URI,
                null,
                null);
    }
    public void downloadPokedex()
    {
        for (int id=1; id<715; id++) //Loading only 12 Pokemons for Testing
        {
            Call<Pokemon> call = service.getPokemon(id);
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
    }
    public void createRetrofit()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PokemonService.class);
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_open)
        {
            Intent gridViewActivity = new Intent(getContext(), GridViewActivity.class);
            startActivity(gridViewActivity);
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
                        deleteDatabase();
                        createRetrofit();
                        downloadPokedex();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){}
                })
                .setIcon(R.drawable.ic_alert_48)
                .show();
    }
}
