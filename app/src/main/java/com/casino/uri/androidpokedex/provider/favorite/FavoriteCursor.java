package com.casino.uri.androidpokedex.provider.favorite;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.casino.uri.androidpokedex.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code favorite} table.
 */
public class FavoriteCursor extends AbstractCursor implements FavoriteModel {
    public FavoriteCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(FavoriteColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code pkdx_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPkdxId() {
        String res = getStringOrNull(FavoriteColumns.PKDX_ID);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        String res = getStringOrNull(FavoriteColumns.NAME);
        return res;
    }

    /**
     * Get the {@code spatk} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpatk() {
        String res = getStringOrNull(FavoriteColumns.SPATK);
        return res;
    }

    /**
     * Get the {@code spdef} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSpdef() {
        String res = getStringOrNull(FavoriteColumns.SPDEF);
        return res;
    }

    /**
     * Get the {@code weight} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getWeight() {
        String res = getStringOrNull(FavoriteColumns.WEIGHT);
        return res;
    }

    /**
     * Get the {@code hp} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getHp() {
        String res = getStringOrNull(FavoriteColumns.HP);
        return res;
    }

    /**
     * Get the {@code created} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCreated() {
        String res = getStringOrNull(FavoriteColumns.CREATED);
        return res;
    }

    /**
     * Get the {@code modified} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getModified() {
        String res = getStringOrNull(FavoriteColumns.MODIFIED);
        return res;
    }

    /**
     * Get the {@code types} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTypes() {
        String res = getStringOrNull(FavoriteColumns.TYPES);
        return res;
    }

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImage() {
        String res = getStringOrNull(FavoriteColumns.IMAGE);
        return res;
    }
}
