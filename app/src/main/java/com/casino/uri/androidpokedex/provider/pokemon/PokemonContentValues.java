package com.casino.uri.androidpokedex.provider.pokemon;



import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.casino.uri.androidpokedex.provider.base.AbstractContentValues;


/**
 * Content values wrapper for the {@code pokemon} table.
 */
public class PokemonContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PokemonColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable PokemonSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }


    public int update(Context context, @Nullable PokemonSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PokemonContentValues putName(@Nullable String value) {
        mContentValues.put(PokemonColumns.NAME, value);
        return this;
    }

    public PokemonContentValues putNameNull() {
        mContentValues.putNull(PokemonColumns.NAME);
        return this;
    }

    public PokemonContentValues putPkdxId(@Nullable String value) {
        mContentValues.put(PokemonColumns.PKDX_ID, value);
        return this;
    }

    public PokemonContentValues putPkdxIdNull() {
        mContentValues.putNull(PokemonColumns.PKDX_ID);
        return this;
    }

    public PokemonContentValues putHp(@Nullable Integer value) {
        mContentValues.put(PokemonColumns.HP, value);
        return this;
    }

    public PokemonContentValues putHpNull() {
        mContentValues.putNull(PokemonColumns.HP);
        return this;
    }

    public PokemonContentValues putSpatk(@Nullable Integer value) {
        mContentValues.put(PokemonColumns.SPATK, value);
        return this;
    }

    public PokemonContentValues putSpatkNull() {
        mContentValues.putNull(PokemonColumns.SPATK);
        return this;
    }

    public PokemonContentValues putSpdef(@Nullable Integer value) {
        mContentValues.put(PokemonColumns.SPDEF, value);
        return this;
    }

    public PokemonContentValues putSpdefNull() {
        mContentValues.putNull(PokemonColumns.SPDEF);
        return this;
    }

    public PokemonContentValues putWeight(@Nullable String value) {
        mContentValues.put(PokemonColumns.WEIGHT, value);
        return this;
    }

    public PokemonContentValues putWeightNull() {
        mContentValues.putNull(PokemonColumns.WEIGHT);
        return this;
    }

    public PokemonContentValues putCreated(@Nullable String value) {
        mContentValues.put(PokemonColumns.CREATED, value);
        return this;
    }

    public PokemonContentValues putCreatedNull() {
        mContentValues.putNull(PokemonColumns.CREATED);
        return this;
    }

    public PokemonContentValues putModified(@Nullable String value) {
        mContentValues.put(PokemonColumns.MODIFIED, value);
        return this;
    }

    public PokemonContentValues putModifiedNull() {
        mContentValues.putNull(PokemonColumns.MODIFIED);
        return this;
    }

    public PokemonContentValues putTypes(@Nullable String value) {
        mContentValues.put(PokemonColumns.TYPES, value);
        return this;
    }

    public PokemonContentValues putTypesNull() {
        mContentValues.putNull(PokemonColumns.TYPES);
        return this;
    }
}
