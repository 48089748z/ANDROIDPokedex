package com.casino.uri.androidpokedex;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FightActivityFragment extends Fragment
{
    TextView won;
    TextView lost;
    Integer nWon = 0;
    Integer nLost = 0;
    String types1 = null;
    String types2 = null;
    Cursor myCursor1 = null;
    Cursor myCursor2 = null;
    EditText search;
    ImageButton fight;
    ImageButton searchBT;
    ImageView fighter1;
    ImageView fighter2;
    TextView name1;
    TextView name2;
    TextView result;
    long id_fighter1 = -1;

    public FightActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fightActivity = inflater.inflate(R.layout.fragment_fight, container, false);
        setHasOptionsMenu(true);
        id_fighter1 = getActivity().getIntent().getLongExtra("fighter1", -1);
        result = (TextView) fightActivity.findViewById(R.id.TVresult);
        fight = (ImageButton) fightActivity.findViewById(R.id.IBfight);
        fighter1 = (ImageView) fightActivity.findViewById(R.id.IVfighter1);
        fighter2 = (ImageView) fightActivity.findViewById(R.id.IVfighter2);
        name1 = (TextView) fightActivity.findViewById(R.id.TVname1);
        name2 = (TextView) fightActivity.findViewById(R.id.TVname2);
        won = (TextView) fightActivity.findViewById(R.id.TVwon);
        lost = (TextView) fightActivity.findViewById(R.id.TVlost);
        search = (EditText) fightActivity.findViewById(R.id.ETsearchFight);
        searchBT = (ImageButton) fightActivity.findViewById(R.id.IBsearchFight);
        won.setTextColor(Color.GREEN);
        lost.setTextColor(Color.RED);
        search.setVisibility(View.INVISIBLE);
        searchBT.setVisibility(View.INVISIBLE);
        fight.setVisibility(View.INVISIBLE);

        fight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fight.setVisibility(View.INVISIBLE);
                if (whoWins()==1)
                {
                    result.setText(name1.getText()+" WON");
                    result.setTextColor(Color.GREEN);
                    Picasso.with(getContext()).load(R.drawable.loser).fit().into(fighter2);
                    nWon++;
                }
                if (whoWins() == 2)
                {
                    result.setText(name2.getText()+" WON");
                    result.setTextColor(Color.RED);
                    Picasso.with(getContext()).load(R.drawable.loser).fit().into(fighter1);
                    nLost++;
                }
                if (whoWins() == 0)
                {
                    result.setText("UNNEFECTIVE TYPES");
                }
                won.setText("Won: "+String.valueOf(nWon));
                lost.setText("Lost: "+String.valueOf(nLost));
            }
        });
        searchBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String pokemonName = search.getText().toString().toLowerCase();
                String parsedPokemonName = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1);
                loadPokemon2(parsedPokemonName);
                search.setVisibility(View.INVISIBLE);
                searchBT.setVisibility(View.INVISIBLE);
                search.setText("");
                search.setHint("Search Pokemon Enemy by name");
            }
        });
        loadPokemon1();
        return fightActivity;
    }
    public void loadPokemon1()
    {
        myCursor1 = getContext().getContentResolver().query(
                PokemonColumns.CONTENT_URI,
                null,
                PokemonColumns._ID + " = ?",
                new String[]{String.valueOf(id_fighter1)},
                "_id");
        myCursor1.moveToNext();
        types1 = myCursor1.getString(myCursor1.getColumnIndex(PokemonColumns.TYPES));
        name1.setText(myCursor1.getString(myCursor1.getColumnIndex(PokemonColumns.NAME)).toUpperCase());
        Picasso.with(getContext()).load(myCursor1.getString(myCursor1.getColumnIndex(PokemonColumns.IMAGE))).fit().into(fighter1);
    }
    public void loadPokemon2(String pokemonName)
    {
        try
        {
            myCursor2 = getContext().getContentResolver().query(
                    PokemonColumns.CONTENT_URI,
                    null,
                    PokemonColumns.NAME + " = ?",
                    new String[]{String.valueOf(pokemonName)},
                    "_id");
            myCursor2.moveToNext();
            types2 = myCursor2.getString(myCursor1.getColumnIndex(PokemonColumns.TYPES));
            name2.setText(myCursor2.getString(myCursor2.getColumnIndex(PokemonColumns.NAME)).toUpperCase());
            Picasso.with(getContext()).load(myCursor2.getString(myCursor2.getColumnIndex(PokemonColumns.IMAGE))).fit().into(fighter2);
            fight.setVisibility(View.VISIBLE);
        }
        catch (Exception nameNotFound) {name2.setText("Not Found");}
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_search)
        {
            search.setVisibility(View.VISIBLE);
            searchBT.setVisibility(View.VISIBLE);
            fight.setVisibility(View.INVISIBLE);
            name1.setText(myCursor1.getString(myCursor1.getColumnIndex(PokemonColumns.NAME)).toUpperCase());
            Picasso.with(getContext()).load(myCursor1.getString(myCursor1.getColumnIndex(PokemonColumns.IMAGE))).fit().into(fighter1);
            Picasso.with(getContext()).load(R.drawable.empty).fit().into(fighter2);
            name2.setText("");
            result.setTextColor(Color.WHITE);
            result.setText("LETS BATTLE");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fight, menu);
    }
    public Integer whoWins()
    {
        if (types1.toUpperCase().equals(types2.toUpperCase())) return 0;

        //TYPE NORMAL COMBINATIONS
        if (types2.toUpperCase().contains("NORMAL")) return 1;
        if (types1.toUpperCase().contains("NORMAL")) return 2;

        //TYPE FIRE COMBINATIONS
        if (types1.toUpperCase().contains("FIRE") && types2.toUpperCase().contains("BUG")) return 1;
        if (types1.toUpperCase().contains("FIRE") && types2.toUpperCase().contains("STEEL")) return 1;
        if (types1.toUpperCase().contains("FIRE") && types2.toUpperCase().contains("GRASS")) return 1;
        if (types1.toUpperCase().contains("FIRE") && types2.toUpperCase().contains("ICE")) return 1;
        if (types1.toUpperCase().contains("BUG") && types2.toUpperCase().contains("FIRE")) return 2;
        if (types1.toUpperCase().contains("STEEL") && types2.toUpperCase().contains("FIRE")) return 2;
        if (types1.toUpperCase().contains("GRASS") && types2.toUpperCase().contains("FIRE")) return 2;
        if (types1.toUpperCase().contains("ICE") && types2.toUpperCase().contains("FIRE")) return 2;

        //TYPE WATER COMBINATIONS
        if (types1.toUpperCase().contains("WATER") && types2.toUpperCase().contains("FIRE")) return 1;
        if (types1.toUpperCase().contains("WATER") && types2.toUpperCase().contains("ROCK")) return 1;
        if (types1.toUpperCase().contains("WATER") && types2.toUpperCase().contains("GROUND")) return 1;
        if (types1.toUpperCase().contains("FIRE") && types2.toUpperCase().contains("WATER")) return 2;
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("WATER")) return 2;
        if (types1.toUpperCase().contains("GROUND") && types2.toUpperCase().contains("WATER")) return 2;

        //TYPE GRASS COMBINATIONS
        if (types1.toUpperCase().contains("GRASS") && types2.toUpperCase().contains("WATER")) return 1;
        if (types1.toUpperCase().contains("GRASS") && types2.toUpperCase().contains("ROCK")) return 1;
        if (types1.toUpperCase().contains("GRASS") && types2.toUpperCase().contains("GROUND")) return 1;
        if (types1.toUpperCase().contains("WATER") && types2.toUpperCase().contains("GRASS")) return 2;
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("GRASS")) return 2;
        if (types1.toUpperCase().contains("GROUND") && types2.toUpperCase().contains("GRASS")) return 2;

        //TYPE FIGHT COMBINATIONS
        if (types1.toUpperCase().contains("FIGHT") && types2.toUpperCase().contains("NORMAL")) return 1;
        if (types1.toUpperCase().contains("FIGHT") && types2.toUpperCase().contains("ROCK")) return 1;
        if (types1.toUpperCase().contains("FIGHT") && types2.toUpperCase().contains("STEEL")) return 1;
        if (types1.toUpperCase().contains("FIGHT") && types2.toUpperCase().contains("ICE")) return 1;
        if (types1.toUpperCase().contains("FIGHT") && types2.toUpperCase().contains("DARK")) return 1;
        if (types1.toUpperCase().contains("NORMAL") && types2.toUpperCase().contains("FIGHT")) return 2;
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("FIGHT")) return 2;
        if (types1.toUpperCase().contains("STEEL") && types2.toUpperCase().contains("FIGHT")) return 2;
        if (types1.toUpperCase().contains("ICE") && types2.toUpperCase().contains("FIGHT")) return 2;
        if (types1.toUpperCase().contains("DARK") && types2.toUpperCase().contains("FIGHT")) return 2;

        //TYPE FLYING COMBINATIONS
        if (types1.toUpperCase().contains("FLYING") && types2.toUpperCase().contains("FIGHT")) return 1;
        if (types1.toUpperCase().contains("FLYING") && types2.toUpperCase().contains("BUG")) return 1;
        if (types1.toUpperCase().contains("FLYING") && types2.toUpperCase().contains("GRASS")) return 1;
        if (types1.toUpperCase().contains("FIGHT") && types2.toUpperCase().contains("FLYING")) return 2;
        if (types1.toUpperCase().contains("BUG") && types2.toUpperCase().contains("FLYING")) return 2;
        if (types1.toUpperCase().contains("GRASS") && types2.toUpperCase().contains("FLYING")) return 2;

        //TYPE POISON COMBINATIONS
        if (types1.toUpperCase().contains("POISON") && types2.toUpperCase().contains("GRASS")) return 1;
        if (types1.toUpperCase().contains("POISON") && types2.toUpperCase().contains("FIRE")) return 1;
        if (types1.toUpperCase().contains("GRASS") && types2.toUpperCase().contains("POISON")) return 2;
        if (types1.toUpperCase().contains("FAIRY") && types2.toUpperCase().contains("FAIRY")) return 2;

        //TYPE GROUND COMBINATIONS
        if (types1.toUpperCase().contains("GROUND") && types2.toUpperCase().contains("POISON")) return 1;
        if (types1.toUpperCase().contains("GROUND") && types2.toUpperCase().contains("ROCK")) return 1;
        if (types1.toUpperCase().contains("GROUND") && types2.toUpperCase().contains("STEEL")) return 1;
        if (types1.toUpperCase().contains("GROUND") && types2.toUpperCase().contains("FIRE")) return 1;
        if (types1.toUpperCase().contains("GROUND") && types2.toUpperCase().contains("ELECTRIC")) return 1;
        if (types1.toUpperCase().contains("POISON") && types2.toUpperCase().contains("GROUND")) return 2;
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("GROUND")) return 2;
        if (types1.toUpperCase().contains("STEEL") && types2.toUpperCase().contains("GROUND")) return 2;
        if (types1.toUpperCase().contains("FIRE") && types2.toUpperCase().contains("GROUND")) return 2;
        if (types1.toUpperCase().contains("ELECTRIC") && types2.toUpperCase().contains("GROUND")) return 2;

        //TYPE ROCK COMBINATIONS
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("FLYING")) return 1;
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("BUG")) return 1;
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("ICE")) return 1;
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("FIRE")) return 1;
        if (types1.toUpperCase().contains("FLYING") && types2.toUpperCase().contains("ROCK")) return 2;
        if (types1.toUpperCase().contains("BUG") && types2.toUpperCase().contains("ROCK")) return 2;
        if (types1.toUpperCase().contains("ICE") && types2.toUpperCase().contains("ROCK")) return 2;
        if (types1.toUpperCase().contains("FIRE") && types2.toUpperCase().contains("ROCK")) return 2;

        //TYPE BUG COMBINATIONS
        if (types1.toUpperCase().contains("BUG") && types2.toUpperCase().contains("GRASS")) return 1;
        if (types1.toUpperCase().contains("BUG") && types2.toUpperCase().contains("PSYCHIC")) return 1;
        if (types1.toUpperCase().contains("BUG") && types2.toUpperCase().contains("DARK")) return 1;
        if (types1.toUpperCase().contains("GRASS") && types2.toUpperCase().contains("BUG")) return 2;
        if (types1.toUpperCase().contains("PSYCHIC") && types2.toUpperCase().contains("BUG")) return 2;
        if (types1.toUpperCase().contains("DARK") && types2.toUpperCase().contains("BUG")) return 2;

        //TYPE GHOST COMBINATIONS
        if (types1.toUpperCase().contains("GHOST") && types2.toUpperCase().contains("PSYCHIC")) return 1;
        if (types1.toUpperCase().contains("PSYCHIC") && types2.toUpperCase().contains("GHOST")) return 2;

        //TYPE STEEL COMBINATIONS
        if (types1.toUpperCase().contains("STEEL") && types2.toUpperCase().contains("ROCK")) return 1;
        if (types1.toUpperCase().contains("STEEL") && types2.toUpperCase().contains("ICE")) return 1;
        if (types1.toUpperCase().contains("STEEL") && types2.toUpperCase().contains("FAIRY")) return 1;
        if (types1.toUpperCase().contains("ROCK") && types2.toUpperCase().contains("STEEL")) return 2;
        if (types1.toUpperCase().contains("ICE") && types2.toUpperCase().contains("STEEL")) return 2;
        if (types1.toUpperCase().contains("FAIRY") && types2.toUpperCase().contains("STEEL")) return 2;

        //TYPE ELECTRIC COMBINATIONS
        if (types1.toUpperCase().contains("ELECTRIC") && types2.toUpperCase().contains("WATER")) return 1;
        if (types1.toUpperCase().contains("ELECTRIC") && types2.toUpperCase().contains("FLYING")) return 1;
        if (types1.toUpperCase().contains("WATER") && types2.toUpperCase().contains("ELECTRIC")) return 2;
        if (types1.toUpperCase().contains("FLYING") && types2.toUpperCase().contains("ELECTRIC")) return 2;

        //TYPE PSYCHIC COMBINATIONS
        if (types1.toUpperCase().contains("PSYCHIC") && types2.toUpperCase().contains("FIGHT")) return 1;
        if (types1.toUpperCase().contains("PSYCHIC") && types2.toUpperCase().contains("POISON")) return 1;
        if (types1.toUpperCase().contains("FIGHT") && types2.toUpperCase().contains("PSYCHIC")) return 2;
        if (types1.toUpperCase().contains("POISON") && types2.toUpperCase().contains("PSYCHIC")) return 2;

        //TYPE ICE COMBINATIONS
        if (types1.toUpperCase().contains("ICE") && types2.toUpperCase().contains("FLYING")) return 1;
        if (types1.toUpperCase().contains("ICE") && types2.toUpperCase().contains("GROUND")) return 1;
        if (types1.toUpperCase().contains("ICE") && types2.toUpperCase().contains("GRASS")) return 1;
        if (types1.toUpperCase().contains("ICE") && types2.toUpperCase().contains("DRAGON")) return 1;
        if (types1.toUpperCase().contains("FLYING") && types2.toUpperCase().contains("ICE")) return 2;
        if (types1.toUpperCase().contains("GROUND") && types2.toUpperCase().contains("ICE")) return 2;
        if (types1.toUpperCase().contains("GRASS") && types2.toUpperCase().contains("ICE")) return 2;
        if (types1.toUpperCase().contains("DRAGON") && types2.toUpperCase().contains("ICE")) return 2;

        //TYPE DARK COMBINATIONS
        if (types1.toUpperCase().contains("DARK") && types2.toUpperCase().contains("GHOST")) return 1;
        if (types1.toUpperCase().contains("DARK") && types2.toUpperCase().contains("PSYCHIC")) return 1;
        if (types1.toUpperCase().contains("GHOST") && types2.toUpperCase().contains("DARK")) return 2;
        if (types1.toUpperCase().contains("PSYCHIC") && types2.toUpperCase().contains("DARK")) return 2;

        //TYPE FAIRY COMBINATIONS
        if (types1.toUpperCase().contains("FAIRY") && types2.toUpperCase().contains("DARK")) return 1;
        if (types1.toUpperCase().contains("FAIRY") && types2.toUpperCase().contains("FIGHT")) return 1;
        if (types1.toUpperCase().contains("FAIRY") && types2.toUpperCase().contains("DRAGON")) return 1;
        if (types1.toUpperCase().contains("DARK") && types2.toUpperCase().contains("FAIRY")) return 2;
        if (types1.toUpperCase().contains("FIGHT") && types2.toUpperCase().contains("FAIRY")) return 2;
        if (types1.toUpperCase().contains("DRAGON") && types2.toUpperCase().contains("FAIRY")) return 2;

        //TYPE DRAGON COMBINATIONS ... NOTHING
        return 0;
    }
}