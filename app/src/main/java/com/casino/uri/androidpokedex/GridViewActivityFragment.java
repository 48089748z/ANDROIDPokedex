package com.casino.uri.androidpokedex;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

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
        View gridViewFragment = inflater.inflate(R.layout.fragment_grid_view, container, false);
        setHasOptionsMenu(true);
        
        pokedex = (GridView) gridViewFragment.findViewById(R.id.GVpokedex);
        search = (EditText) gridViewFragment.findViewById(R.id.ETsearch);
        searchB = (ImageButton) gridViewFragment.findViewById(R.id.BTsearch);
        adapter = new PokemonDatabaseAdapter(
                getContext(),
                R.layout.gridview_layout,
                null,
                new String[] { PokemonColumns.NAME, PokemonColumns.MODIFIED },
                new int[] { R.id.TVname, R.id.IVimage},
                0);

        pokedex.setAdapter(adapter);
        pokedex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent pokemonDetails = new Intent(getContext(), DetailsActivity.class);
                pokemonDetails.putExtra("cursor_id", id);
                startActivity(pokemonDetails);
            }
        });

        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPokemon(search.getText().toString());
                search.setVisibility(View.INVISIBLE);
                searchB.setVisibility(View.INVISIBLE);
                search.setText("");
                search.setHint("Search Pokemon by name");
            }
        });

        pokedex.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {


                return false;
            }
        });
        return gridViewFragment;
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
        return super.onOptionsItemSelected(item);
    }
    public void searchPokemon(String pokemonName)
    {
        Intent detailsActivityFragment = new Intent(getContext(), DetailsActivity.class);
        detailsActivityFragment.putExtra("cursor_id", -1);
        detailsActivityFragment.putExtra("pokemonName", pokemonName);
        startActivity(detailsActivityFragment);
    }
}
