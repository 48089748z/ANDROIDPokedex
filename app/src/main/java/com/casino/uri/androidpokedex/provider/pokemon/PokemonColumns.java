package com.casino.uri.androidpokedex.provider.pokemon;

import android.net.Uri;
import android.provider.BaseColumns;

import com.casino.uri.androidpokedex.provider.PokemonsProvider;


/**
 * A Pokemon.
 */
public class PokemonColumns implements BaseColumns {
    public static final String TABLE_NAME = "pokemon";
    public static final Uri CONTENT_URI = Uri.parse(PokemonsProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String NAME = "name";

    public static final String PKDX_ID = "pkdx_id";

    public static final String HP = "hp";

    public static final String SPATK = "spAtk";

    public static final String SPDEF = "spDef";

    public static final String WEIGHT = "weight";

    public static final String CREATED = "created";

    public static final String MODIFIED = "modified";

    public static final String TYPES = "types";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            NAME,
            PKDX_ID,
            HP,
            SPATK,
            SPDEF,
            WEIGHT,
            CREATED,
            MODIFIED,
            TYPES
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(PKDX_ID) || c.contains("." + PKDX_ID)) return true;
            if (c.equals(HP) || c.contains("." + HP)) return true;
            if (c.equals(SPATK) || c.contains("." + SPATK)) return true;
            if (c.equals(SPDEF) || c.contains("." + SPDEF)) return true;
            if (c.equals(WEIGHT) || c.contains("." + WEIGHT)) return true;
            if (c.equals(CREATED) || c.contains("." + CREATED)) return true;
            if (c.equals(MODIFIED) || c.contains("." + MODIFIED)) return true;
            if (c.equals(TYPES) || c.contains("." + TYPES)) return true;
        }
        return false;
    }

}
