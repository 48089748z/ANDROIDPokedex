package com.casino.uri.androidpokedex.provider.pokemon;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.casino.uri.androidpokedex.provider.base.AbstractCursor;


/**
 * Cursor wrapper for the {@code pokemon} table.
 */
public class PokemonCursor extends AbstractCursor implements PokemonModel {
    public PokemonCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(PokemonColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        String res = getStringOrNull(PokemonColumns.NAME);
        return res;
    }

    /**
     * Get the {@code pkdx_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPkdxId() {
        String res = getStringOrNull(PokemonColumns.PKDX_ID);
        return res;
    }

    /**
     * Get the {@code hp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getHp() {
        Integer res = getIntegerOrNull(PokemonColumns.HP);
        return res;
    }

    /**
     * Get the {@code spatk} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getSpatk() {
        Integer res = getIntegerOrNull(PokemonColumns.SPATK);
        return res;
    }

    /**
     * Get the {@code spdef} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getSpdef() {
        Integer res = getIntegerOrNull(PokemonColumns.SPDEF);
        return res;
    }

    /**
     * Get the {@code weight} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getWeight() {
        String res = getStringOrNull(PokemonColumns.WEIGHT);
        return res;
    }

    /**
     * Get the {@code created} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCreated() {
        String res = getStringOrNull(PokemonColumns.CREATED);
        return res;
    }

    /**
     * Get the {@code modified} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getModified() {
        String res = getStringOrNull(PokemonColumns.MODIFIED);
        return res;
    }

    /**
     * Get the {@code types} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTypes() {
        String res = getStringOrNull(PokemonColumns.TYPES);
        return res;
    }
}
