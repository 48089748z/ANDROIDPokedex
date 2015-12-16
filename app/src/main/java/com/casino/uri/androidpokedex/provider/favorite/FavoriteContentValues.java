package com.casino.uri.androidpokedex.provider.favorite;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.casino.uri.androidpokedex.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code favorite} table.
 */
public class FavoriteContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return FavoriteColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable FavoriteSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable FavoriteSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public FavoriteContentValues putPkdxId(@Nullable String value) {
        mContentValues.put(FavoriteColumns.PKDX_ID, value);
        return this;
    }

    public FavoriteContentValues putPkdxIdNull() {
        mContentValues.putNull(FavoriteColumns.PKDX_ID);
        return this;
    }

    public FavoriteContentValues putName(@Nullable String value) {
        mContentValues.put(FavoriteColumns.NAME, value);
        return this;
    }

    public FavoriteContentValues putNameNull() {
        mContentValues.putNull(FavoriteColumns.NAME);
        return this;
    }

    public FavoriteContentValues putSpatk(@Nullable String value) {
        mContentValues.put(FavoriteColumns.SPATK, value);
        return this;
    }

    public FavoriteContentValues putSpatkNull() {
        mContentValues.putNull(FavoriteColumns.SPATK);
        return this;
    }

    public FavoriteContentValues putSpdef(@Nullable String value) {
        mContentValues.put(FavoriteColumns.SPDEF, value);
        return this;
    }

    public FavoriteContentValues putSpdefNull() {
        mContentValues.putNull(FavoriteColumns.SPDEF);
        return this;
    }

    public FavoriteContentValues putWeight(@Nullable String value) {
        mContentValues.put(FavoriteColumns.WEIGHT, value);
        return this;
    }

    public FavoriteContentValues putWeightNull() {
        mContentValues.putNull(FavoriteColumns.WEIGHT);
        return this;
    }

    public FavoriteContentValues putHp(@Nullable String value) {
        mContentValues.put(FavoriteColumns.HP, value);
        return this;
    }

    public FavoriteContentValues putHpNull() {
        mContentValues.putNull(FavoriteColumns.HP);
        return this;
    }

    public FavoriteContentValues putCreated(@Nullable String value) {
        mContentValues.put(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteContentValues putCreatedNull() {
        mContentValues.putNull(FavoriteColumns.CREATED);
        return this;
    }

    public FavoriteContentValues putModified(@Nullable String value) {
        mContentValues.put(FavoriteColumns.MODIFIED, value);
        return this;
    }

    public FavoriteContentValues putModifiedNull() {
        mContentValues.putNull(FavoriteColumns.MODIFIED);
        return this;
    }

    public FavoriteContentValues putTypes(@Nullable String value) {
        mContentValues.put(FavoriteColumns.TYPES, value);
        return this;
    }

    public FavoriteContentValues putTypesNull() {
        mContentValues.putNull(FavoriteColumns.TYPES);
        return this;
    }

    public FavoriteContentValues putImage(@Nullable String value) {
        mContentValues.put(FavoriteColumns.IMAGE, value);
        return this;
    }

    public FavoriteContentValues putImageNull() {
        mContentValues.putNull(FavoriteColumns.IMAGE);
        return this;
    }
}
