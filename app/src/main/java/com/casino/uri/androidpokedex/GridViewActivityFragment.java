package com.casino.uri.androidpokedex;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.casino.uri.androidpokedex.com.pojos.Pokemon;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;

import java.util.ArrayList;

public class GridViewActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    OnPokemonSelectedListener listener;
    ArrayList<Pokemon> items = new ArrayList<>();
    SharedPreferences myPreferences;
    MediaPlayer music;
    MediaPlayer sounds;
    PokemonDatabaseAdapterGV GVadapter;
    PokemonAdapterLV LVadapter;
    GridView pokedexGV;
    ListView autoCompleteLV;
    TextView results;
    EditText search;
    ImageButton searchBT;
    public void onStart()
    {
        super.onStart();
        getLoaderManager().restartLoader(0, null, this);
        myPreferences = getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
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
    public GridViewActivityFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View gridViewFragment = inflater.inflate(R.layout.fragment_grid_view, container, false);
        setHasOptionsMenu(true);

        results = (TextView) gridViewFragment.findViewById(R.id.TVresults);
        autoCompleteLV = (ListView) gridViewFragment.findViewById(R.id.LVautocomplete);
        pokedexGV = (GridView) gridViewFragment.findViewById(R.id.GVpokedex);
        search = (EditText) gridViewFragment.findViewById(R.id.ETsearch);
        searchBT = (ImageButton) gridViewFragment.findViewById(R.id.IBsearchFight);
        music = MediaPlayer.create(getContext(), R.raw.song_pokedex);
        search.setVisibility(View.INVISIBLE);
        searchBT.setVisibility(View.INVISIBLE);
        GVadapter = new PokemonDatabaseAdapterGV(
                getContext(),
                R.layout.gridview_layout,
                null,
                new String[] { PokemonColumns.NAME, PokemonColumns.IMAGE },
                new int[] { R.id.TVname, R.id.IVimage},
                0);
        pokedexGV.setAdapter(GVadapter);
        pokedexGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        checkSelected(item, id);
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        searchBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String pokemonName = search.getText().toString().toLowerCase();
                    String parsedPokemonName = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1);
                    searchPokemon(parsedPokemonName);
                    search.setVisibility(View.INVISIBLE);
                    searchBT.setVisibility(View.INVISIBLE);
                    search.setText("");
                    search.setHint("Search Pokemon by name");
                } catch (Exception noName) {
                    searchPokemon(" ");
                }
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                autocomplete();
            }
        });
        LVadapter = new PokemonAdapterLV(getContext(), 0, items);
        autoCompleteLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Pokemon clicked = (Pokemon) autoCompleteLV.getItemAtPosition(position);
                search.setText(clicked.getName());
            }
        });
        autoCompleteLV.setAdapter(LVadapter);
        return gridViewFragment;
    }
    public void autocomplete()
    {
        LVadapter.clear();
        results.setText("");
        Cursor autocompleteCursor = getContext().getContentResolver().query(
                PokemonColumns.CONTENT_URI,
                null,
                PokemonColumns.NAME+" LIKE ?",
                new String[]{search.getText().toString()+"%"},
                "_id");

        if (autocompleteCursor.getCount()!=0 && autocompleteCursor.getCount()<150)
        {
            results.setText(String.valueOf(autocompleteCursor.getCount())+" Results ");
            for (int x=0; x<autocompleteCursor.getCount(); x++)
            {
                autocompleteCursor.moveToNext();
                Pokemon toAdd = new Pokemon();
                toAdd.setName(autocompleteCursor.getString(autocompleteCursor.getColumnIndex(PokemonColumns.NAME)));
                toAdd.setEvYield(autocompleteCursor.getString(autocompleteCursor.getColumnIndex(PokemonColumns.IMAGE)));
                LVadapter.add(toAdd);
            }
        }
    }
    public boolean checkSelected(MenuItem item, long id)
    {
        int menu_id = item.getItemId();
        if (menu_id == R.id.item_favorite)
        {
            if (myPreferences.getBoolean("soundsON", true))
            {
                sounds = MediaPlayer.create(getContext(), R.raw.sound_favorite);
                sounds.start();
            }
            addToFavorites(id);
            return true;
        }
        if (menu_id == R.id.item_fight)
        {
            fight(id);
            return true;
        }
        if (menu_id == R.id.item_details)
        {
            showDetails(id);
            return true;
        }
        return true;
    }
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        return new CursorLoader(getContext(),
                PokemonColumns.CONTENT_URI,
                null,
                null,
                null,
                "_id");
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        GVadapter.swapCursor(data);}
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        GVadapter.swapCursor(null);}
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_grid_view, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_search)
        {
            if (myPreferences.getBoolean("soundsON", true)) {
                sounds = MediaPlayer.create(getContext(), R.raw.sound_search);
                sounds.start();
            }
            search.setVisibility(View.VISIBLE);
            searchBT.setVisibility(View.VISIBLE);
            return true;
        }
        if (id == R.id.action_favorites)
        {
            Intent favoritesActivityFragment = new Intent(getContext(), FavoritesActivity.class);
            startActivity(favoritesActivityFragment);
        }
        return super.onOptionsItemSelected(item);
    }
    public void searchPokemon(String pokemonName)
    {
        Intent detailsActivityFragment = new Intent(getContext(), DetailsActivity.class);
        detailsActivityFragment.putExtra("pokemonName", pokemonName);
        startActivity(detailsActivityFragment);
    }
    public void showDetails(long id)
    {
        listener.onPokemonSelected(id);
    }

    public void addToFavorites(long id)
    {
        Intent favoritesActivity = new Intent(getContext(), FavoritesActivity.class);
        favoritesActivity.putExtra("favorite_id", id);
        startActivity(favoritesActivity);
    }
    public void fight(long id)
    {
        Intent fightActivity = new Intent(getContext(), FightActivity.class);
        fightActivity.putExtra("fighter1", id);
        startActivity(fightActivity);
    }
    public void onAttach(Context context)
    {
        super.onAttach(context);
        listener = (OnPokemonSelectedListener) context;

    }
    public interface OnPokemonSelectedListener
    {
        void onPokemonSelected(long id);
    }

}