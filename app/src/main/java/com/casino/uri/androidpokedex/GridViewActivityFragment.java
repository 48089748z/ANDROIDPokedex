package com.casino.uri.androidpokedex;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;

public class GridViewActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    PokemonDatabaseAdapter adapter;
    GridView pokedex;
    EditText search;
    ImageButton searchB;
    public void onStart()
    {
        super.onStart();
        getLoaderManager().restartLoader(0, null, this);
        search.setVisibility(View.INVISIBLE);
        searchB.setVisibility(View.INVISIBLE);
    }
    public GridViewActivityFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View gridViewFragment = inflater.inflate(R.layout.fragment_grid_view, container, false);
        setHasOptionsMenu(true);

        pokedex = (GridView) gridViewFragment.findViewById(R.id.GVpokedex);
        search = (EditText) gridViewFragment.findViewById(R.id.ETsearch);
        searchB = (ImageButton) gridViewFragment.findViewById(R.id.BTsearch);
        adapter = new PokemonDatabaseAdapter(
                getContext(),
                R.layout.gridview_layout,
                null,
                new String[] { PokemonColumns.NAME, PokemonColumns.IMAGE },
                new int[] { R.id.TVname, R.id.IVimage},
                0);

        pokedex.setAdapter(adapter);
        pokedex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        checkSelected(item, id);
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonName = search.getText().toString().toLowerCase();
                String parsedPokemonName = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1);
                searchPokemon(parsedPokemonName);
                search.setVisibility(View.INVISIBLE);
                searchB.setVisibility(View.INVISIBLE);
                search.setText("");
                search.setHint("Search Pokemon by name");
            }
        });
        return gridViewFragment;
    }
    public boolean checkSelected(MenuItem item, long id)
    {
        int menu_id = item.getItemId();
        if (menu_id == R.id.item_favorite)
        {
            addToFavorites(id);
            return true;
        }
        if (menu_id == R.id.item_fight)
        {
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
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {adapter.swapCursor(data);}
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {adapter.swapCursor(null);}
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
            search.setVisibility(View.VISIBLE);
            searchB.setVisibility(View.VISIBLE);
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
        Intent pokemonDetails = new Intent(getContext(), DetailsActivity.class);
        pokemonDetails.putExtra("grid_id", id);
        startActivity(pokemonDetails);
    }

    public void addToFavorites(long id)
    {
        Intent favoritesActivity = new Intent(getContext(), FavoritesActivity.class);
        favoritesActivity.putExtra("favorite_id", id);
        startActivity(favoritesActivity);
    }
}
