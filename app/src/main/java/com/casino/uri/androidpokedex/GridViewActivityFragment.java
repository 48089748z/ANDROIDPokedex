package com.casino.uri.androidpokedex;
import android.database.Observable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import com.casino.uri.androidpokedex.com.pojos.Pokemon;
import com.casino.uri.androidpokedex.com.pojos.PokemonAdapter;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class GridViewActivityFragment extends Fragment
{
    PokemonService service;
    Retrofit retrofit;
    private ArrayList<Pokemon> items = new ArrayList<>();
    private String BASE_URL = "http://pokeapi.co/api/v1/";

    public GridViewActivityFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View gridViewFragment = inflater.inflate(R.layout.fragment_grid_view, container, false);

        createRetrofit();
        final PokemonAdapter adapter = new PokemonAdapter(getContext(), 0, items);
        GridView pokedex = (GridView) gridViewFragment.findViewById(R.id.GVpokedex);
        pokedex.setAdapter(adapter);
        pokedex.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

            }
        });
        for (int id=140; id<152; id++) //Loading only 12 Pokemons for Testing
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
                        adapter.add(pokemon);
                        adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onFailure(Throwable t) {}
            });
        }
        //DownloadPokemons downloadPokemons = new DownloadPokemons();
        //downloadPokemons.execute();
        return gridViewFragment;
    }

    class DownloadPokemons extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] params)
        {
            return null;
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
}
