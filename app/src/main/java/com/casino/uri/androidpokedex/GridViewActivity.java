package com.casino.uri.androidpokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GridViewActivity extends AppCompatActivity implements GridViewActivityFragment.OnPokemonSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("POKEMONS LIST");
        setSupportActionBar(toolbar);
    }
    public void onPokemonSelected(long id)
    {
        boolean tablet = getResources().getBoolean(R.bool.tablet);
        if (tablet)
        {
            DetailsActivityFragment detailFragment = (DetailsActivityFragment)
                    getSupportFragmentManager().findFragmentById(R.id.detailsfragment);
                    detailFragment.loadPokemonFromActivity(id);
        }
        else
        {
            Intent detailsActivity = new Intent(this, DetailsActivity.class);
            detailsActivity.putExtra("grid_id", id);
            startActivity(detailsActivity);
        }
    }
}
