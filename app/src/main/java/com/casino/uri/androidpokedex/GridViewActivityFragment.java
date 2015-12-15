package com.casino.uri.androidpokedex;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;

public class GridViewActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    PokemonDatabaseAdapter adapter;
    public void onStart()
    {
        super.onStart();
        getLoaderManager().restartLoader(0, null, this);
    }
    public GridViewActivityFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View gridViewFragment = inflater.inflate(R.layout.fragment_grid_view, container, false);
        setHasOptionsMenu(true);
        
        GridView pokedex = (GridView) gridViewFragment.findViewById(R.id.GVpokedex);
        adapter = new PokemonDatabaseAdapter(
                getContext(),
                R.layout.gridview_layout,
                null,
                new String[] { PokemonColumns.NAME, PokemonColumns.MODIFIED },
                new int[] { R.id.TVname, R.id.IVimage},
                0);

        pokedex.setAdapter(adapter);
        pokedex.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
}
