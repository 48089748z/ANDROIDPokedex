package com.casino.uri.androidpokedex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.casino.uri.androidpokedex.provider.favorite.FavoriteColumns;
import com.casino.uri.androidpokedex.provider.favorite.FavoriteContentValues;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;

public class FavoritesActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    SharedPreferences myPreferences;
    MediaPlayer music;
    MediaPlayer sounds;
    Cursor myCursor;
    long cursor_id = -1;
    GridView favorites;
    PokemonDatabaseAdapterGV adapter;
    public FavoritesActivityFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View favoritesActivityFragment = inflater.inflate(R.layout.fragment_favorites, container, false);
        setHasOptionsMenu(true);
        favorites = (GridView) favoritesActivityFragment.findViewById(R.id.GVfavorites);
        music = MediaPlayer.create(getContext(), R.raw.song_healing);
        adapter = new PokemonDatabaseAdapterGV(
                getContext(),
                R.layout.gridview_layout,
                null,
                new String[] { FavoriteColumns.NAME, FavoriteColumns.MODIFIED },
                new int[] { R.id.TVname, R.id.IVimage},
                0);
        favorites.setAdapter(adapter);
        favorites.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteSelected(id);
                return false;
            }
        });
        favorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detailsSelected(id);
            }
        });
        cursor_id = getActivity().getIntent().getLongExtra("favorite_id", -1);
        if (cursor_id != -1)
        {
            savePokemon(cursor_id);
        }
        return favoritesActivityFragment;
    }

    public void savePokemon(long id)
    {
        myCursor = getContext().getContentResolver().query(
                PokemonColumns.CONTENT_URI,
                null,
                PokemonColumns._ID + " = ?",
                new String[]{String.valueOf(id)},
                "_id");
        if (myCursor.getCount()!=0)
        {
            myCursor.moveToNext();
            FavoriteContentValues values = new FavoriteContentValues();
            values.putPkdxId(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.PKDX_ID)));
            values.putName(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.NAME)));
            values.putSpatk(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.SPATK)));
            values.putSpdef(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.SPDEF)));
            values.putWeight(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.WEIGHT)));
            values.putHp(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.HP)));
            values.putCreated(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.CREATED)));
            values.putModified(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.MODIFIED)));
            values.putTypes(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.TYPES)));
            values.putImage(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.IMAGE)));
            getContext().getContentResolver().insert(FavoriteColumns.CONTENT_URI, values.values());
        }
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        return new CursorLoader(getContext(),
                FavoriteColumns.CONTENT_URI,
                null,
                null,
                null,
                "_id");
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {adapter.swapCursor(data);}
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {adapter.swapCursor(null);}
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_favorites, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_delete)
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
                .setMessage("Are you sure you want to delete all the Favorite Pokemons?\n\nThis cannot be undone! You will have to add them again manually.\n\nIf you want to remove only one, keep it pressed.")
                .setPositiveButton("Delete All", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if (myPreferences.getBoolean("soundsON", true)) {
                            sounds = MediaPlayer.create(getContext(), R.raw.sound_favorites_deleted);
                            sounds.start();
                        }
                        deleteDatabase();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(R.drawable.ic_alert_48)
                .show();
    }
    public void deleteDatabase()
    {
        getContext().getContentResolver().delete(
                FavoriteColumns.CONTENT_URI,
                null,
                null);
    }
    public void deleteSelected(long id)
    {
        getContext().getContentResolver().delete(
                FavoriteColumns.CONTENT_URI,
                FavoriteColumns._ID + " = ?",
                new String[]{String.valueOf(id)});
    }
    public void detailsSelected(long id)
    {
        Intent pokemonDetails = new Intent(getContext(), DetailsActivity.class);
        pokemonDetails.putExtra("favorite_id", id);
        startActivity(pokemonDetails);
    }
}
