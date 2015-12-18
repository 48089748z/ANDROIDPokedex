package com.casino.uri.androidpokedex;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.casino.uri.androidpokedex.provider.favorite.FavoriteColumns;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;
import com.squareup.picasso.Picasso;


public class DetailsActivityFragment extends Fragment
{
    SharedPreferences myPreferences;
    MediaPlayer sounds;
    MediaPlayer music;
    Cursor myCursor = null;
    String pokemonName = null;
    private long grid_id = -1;
    private long favorite_id = -1;
    TextView name;
    TextView pkdx_id;
    TextView hp;
    TextView weight;
    TextView types;
    TextView spAtk;
    TextView spDef;
    TextView created;
    ImageView image;

    public void onStart()
    {
        super.onStart();
        if (myPreferences.getBoolean("musicON", true))
        {
            music.start();
        }
    }
    public void onStop()
    {
        super.onStop();
        if (music.isPlaying()) {music.stop();}
    }
    public DetailsActivityFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailsActivityFragment = inflater.inflate(R.layout.fragment_details, container, false);

        myPreferences = getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        name = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsName);
        pkdx_id = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsId);
        hp = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsHp);
        weight = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsWeight);
        spAtk = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsSpAtk);
        spDef = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsSpDef);
        types = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsTypes);
        created = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsCreated);
        image = (ImageView) detailsActivityFragment.findViewById(R.id.IVdetailsImage);
        music = MediaPlayer.create(getContext(), R.raw.song_varidian);

        pokemonName = getActivity().getIntent().getStringExtra("pokemonName");
        grid_id = getActivity().getIntent().getLongExtra("grid_id", -1);
        favorite_id = getActivity().getIntent().getLongExtra("favorite_id", -1);
        loadPokemon();
        return detailsActivityFragment;
    }
    public void loadPokemon()
    {
        if (pokemonName == null && grid_id != -1 && favorite_id == -1) //LOADS GRID ID
        {
            myCursor = getContext().getContentResolver().query(
                    PokemonColumns.CONTENT_URI,
                    null,
                    PokemonColumns._ID + " = ?",
                    new String[]{String.valueOf(grid_id)},
                    "_id");
            fillPokemon();
        }
        if (pokemonName != null && grid_id == -1 && favorite_id == -1) //LOADS THE SEARCH BY NAME
        {
            try
            {
                myCursor = getContext().getContentResolver().query(
                        PokemonColumns.CONTENT_URI,
                        null,
                        PokemonColumns.NAME + " = ?",
                        new String[]{String.valueOf(pokemonName)},
                        "_id");
                fillPokemon();
            }
            catch(Exception nameNotFound)
            {
                name.setText("POKEMON NOT FOUND");
                Picasso.with(getContext()).load(R.drawable.pokemonnotfound).fit().into(image);
                if (myPreferences.getBoolean("soundsON", true))
                {
                    sounds = MediaPlayer.create(getContext(), R.raw.sound_notfound);
                    sounds.start();
                }

            }
        }
        if (pokemonName == null && grid_id == -1 && favorite_id != -1) //LOADS FAVORITE ID
        {
            myCursor = getContext().getContentResolver().query(
                    FavoriteColumns.CONTENT_URI,
                    null,
                    FavoriteColumns._ID + " = ?",
                    new String[]{String.valueOf(favorite_id)},
                    "_id");
            fillFavorite();
        }
    }
    public void fillPokemon()
    {
        if (myCursor != null)
        {
            myCursor.moveToNext();
            name.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.NAME)).toUpperCase());
            pkdx_id.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.PKDX_ID)));
            hp.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.HP)) + " hp");
            weight.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.WEIGHT)) + " kg");
            spAtk.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.SPATK)));
            spDef.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.SPDEF)));
            types.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.TYPES)));
            created.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.CREATED)).substring(0, 10));
            Picasso.with(getContext()).load(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.IMAGE))).fit().into(image);
            soundFound();
        }
    }
    public void fillFavorite()
    {
        if (myCursor != null)
        {
            myCursor.moveToNext();
            name.setText(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.NAME)).toUpperCase());
            pkdx_id.setText(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.PKDX_ID)));
            hp.setText(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.HP)) + " hp");
            weight.setText(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.WEIGHT)) + " kg");
            spAtk.setText(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.SPATK)));
            spDef.setText(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.SPDEF)));
            types.setText(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.TYPES)));
            created.setText(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.CREATED)).substring(0, 10));
            Picasso.with(getContext()).load(myCursor.getString(myCursor.getColumnIndex(FavoriteColumns.IMAGE))).fit().into(image);
            soundFound();
        }
    }
    public void soundFound()
    {
        if (myPreferences.getBoolean("soundsON", true))
        {
            sounds = MediaPlayer.create(getContext(), R.raw.sound_found);
            sounds.start();
        }
    }
}
