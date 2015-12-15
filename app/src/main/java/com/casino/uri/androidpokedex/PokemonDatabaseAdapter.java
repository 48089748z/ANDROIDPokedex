package com.casino.uri.androidpokedex;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;
import com.squareup.picasso.Picasso;

public class PokemonDatabaseAdapter extends SimpleCursorAdapter
{
    Context context;

    public PokemonDatabaseAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Cursor myCursor = getCursor();
        myCursor.moveToPosition(position);

        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.gridview_layout, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.TVname);
        ImageView image = (ImageView) convertView.findViewById(R.id.IVimage);

        String URL = myCursor.getString(myCursor.getColumnIndex(PokemonColumns.MODIFIED));

        name.setText(myCursor.getString(myCursor.getColumnIndex(PokemonColumns.NAME)));
        Picasso.with(context).load(URL).fit().into(image);
        return convertView;
    }
}