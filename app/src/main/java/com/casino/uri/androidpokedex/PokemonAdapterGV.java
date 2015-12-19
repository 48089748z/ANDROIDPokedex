package com.casino.uri.androidpokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.casino.uri.androidpokedex.com.pojos.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapterGV extends ArrayAdapter<Pokemon>
{
    final private String IMAGE_BASE_URL = "http://pokeapi.co/media/img/";
    public PokemonAdapterGV(Context context, int resource, List<Pokemon> objects)
    {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Pokemon pokemon = getItem(position);
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.gridview_layout, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.TVname);
        ImageView image = (ImageView) convertView.findViewById(R.id.IVimage);
        name.setText(pokemon.getName());
        Picasso.with(getContext()).load(IMAGE_BASE_URL+pokemon.getPkdxId()+".png").fit().into(image);
        return convertView;
    }
}
