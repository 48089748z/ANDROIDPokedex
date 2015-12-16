package com.casino.uri.androidpokedex;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;
import com.squareup.picasso.Picasso;

public class DetailsActivityFragment extends Fragment
{
    Cursor myCursor = null;
    private long cursor_id = -1;
    TextView name;
    TextView pkdx_id;
    TextView hp;
    TextView weight;
    TextView types;
    TextView spAtk;
    TextView spDef;
    TextView created;
    ImageView image;

    public DetailsActivityFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailsActivityFragment = inflater.inflate(R.layout.fragment_details, container, false);

        name = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsName);
        pkdx_id = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsId);
        hp = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsHp);
        weight = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsWeight);
        spAtk = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsSpAtk);
        spDef = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsSpDef);
        types = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsTypes);
        created = (TextView) detailsActivityFragment.findViewById(R.id.TVdetailsCreated);
        image = (ImageView) detailsActivityFragment.findViewById(R.id.IVdetailsImage);

        cursor_id = getActivity().getIntent().getLongExtra("cursor_id", -1);
        if (cursor_id != -1)
        {
            loadPokemon(cursor_id, null);
        }
        else
        {
            loadPokemon(-1, getActivity().getIntent().getStringExtra("pokemonName"));
        }
        return detailsActivityFragment;
    }
    public void loadPokemon(long id, String pokemonName)
    {
        if (pokemonName == null)
        {
            myCursor = getContext().getContentResolver().query(
                    PokemonColumns.CONTENT_URI,
                    null,
                    PokemonColumns._ID + " = ?",
                    new String[]{String.valueOf(id)},
                    "_id");
            fillFields();
        }
        else
        {
            try
            {
                myCursor = getContext().getContentResolver().query(
                        PokemonColumns.CONTENT_URI,
                        null,
                        PokemonColumns.NAME + " = ?",
                        new String[]{String.valueOf(pokemonName)},
                        "_id");
                fillFields();
            }
            catch(Exception nameNotFound)
            {
                name.setText("POKEMON NOT FOUND");
                Picasso.with(getContext()).load(R.drawable.pokemonnotfound).fit().into(image);
            }
        }
    }
    public void fillFields()
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
            Picasso.with(getContext()).load(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.MODIFIED))).fit().into(image);
        }
    }
}
