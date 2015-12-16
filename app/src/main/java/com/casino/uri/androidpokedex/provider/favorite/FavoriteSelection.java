package com.casino.uri.androidpokedex.provider.favorite;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.casino.uri.androidpokedex.provider.base.AbstractSelection;

/**
 * Selection for the {@code favorite} table.
 */
public class FavoriteSelection extends AbstractSelection<FavoriteSelection> {
    @Override
    protected Uri baseUri() {
        return FavoriteColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FavoriteCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FavoriteCursor query(Context context) {
        return query(context, null);
    }


    public FavoriteSelection id(long... value) {
        addEquals("favorite." + FavoriteColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection idNot(long... value) {
        addNotEquals("favorite." + FavoriteColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection orderById(boolean desc) {
        orderBy("favorite." + FavoriteColumns._ID, desc);
        return this;
    }

    public FavoriteSelection orderById() {
        return orderById(false);
    }

    public FavoriteSelection pkdxId(String... value) {
        addEquals(FavoriteColumns.PKDX_ID, value);
        return this;
    }

    public FavoriteSelection pkdxIdNot(String... value) {
        addNotEquals(FavoriteColumns.PKDX_ID, value);
        return this;
    }

    public FavoriteSelection pkdxIdLike(String... value) {
        addLike(FavoriteColumns.PKDX_ID, value);
        return this;
    }

    public FavoriteSelection pkdxIdContains(String... value) {
        addContains(FavoriteColumns.PKDX_ID, value);
        return this;
    }

    public FavoriteSelection pkdxIdStartsWith(String... value) {
        addStartsWith(FavoriteColumns.PKDX_ID, value);
        return this;
    }

    public FavoriteSelection pkdxIdEndsWith(String... value) {
        addEndsWith(FavoriteColumns.PKDX_ID, value);
        return this;
    }

    public FavoriteSelection orderByPkdxId(boolean desc) {
        orderBy(FavoriteColumns.PKDX_ID, desc);
        return this;
    }

    public FavoriteSelection orderByPkdxId() {
        orderBy(FavoriteColumns.PKDX_ID, false);
        return this;
    }

    public FavoriteSelection name(String... value) {
        addEquals(FavoriteColumns.NAME, value);
        return this;
    }

    public FavoriteSelection nameNot(String... value) {
        addNotEquals(FavoriteColumns.NAME, value);
        return this;
    }

    public FavoriteSelection nameLike(String... value) {
        addLike(FavoriteColumns.NAME, value);
        return this;
    }

    public FavoriteSelection nameContains(String... value) {
        addContains(FavoriteColumns.NAME, value);
        return this;
    }

    public FavoriteSelection nameStartsWith(String... value) {
        addStartsWith(FavoriteColumns.NAME, value);
        return this;
    }

    public FavoriteSelection nameEndsWith(String... value) {
        addEndsWith(FavoriteColumns.NAME, value);
        return this;
    }

    public FavoriteSelection orderByName(boolean desc) {
        orderBy(FavoriteColumns.NAME, desc);
        return this;
    }

    public FavoriteSelection orderByName() {
        orderBy(FavoriteColumns.NAME, false);
        return this;
    }

    public FavoriteSelection spatk(String... value) {
        addEquals(FavoriteColumns.SPATK, value);
        return this;
    }

    public FavoriteSelection spatkNot(String... value) {
        addNotEquals(FavoriteColumns.SPATK, value);
        return this;
    }

    public FavoriteSelection spatkLike(String... value) {
        addLike(FavoriteColumns.SPATK, value);
        return this;
    }

    public FavoriteSelection spatkContains(String... value) {
        addContains(FavoriteColumns.SPATK, value);
        return this;
    }

    public FavoriteSelection spatkStartsWith(String... value) {
        addStartsWith(FavoriteColumns.SPATK, value);
        return this;
    }

    public FavoriteSelection spatkEndsWith(String... value) {
        addEndsWith(FavoriteColumns.SPATK, value);
        return this;
    }

    public FavoriteSelection orderBySpatk(boolean desc) {
        orderBy(FavoriteColumns.SPATK, desc);
        return this;
    }

    public FavoriteSelection orderBySpatk() {
        orderBy(FavoriteColumns.SPATK, false);
        return this;
    }

    public FavoriteSelection spdef(String... value) {
        addEquals(FavoriteColumns.SPDEF, value);
        return this;
    }

    public FavoriteSelection spdefNot(String... value) {
        addNotEquals(FavoriteColumns.SPDEF, value);
        return this;
    }

    public FavoriteSelection spdefLike(String... value) {
        addLike(FavoriteColumns.SPDEF, value);
        return this;
    }

    public FavoriteSelection spdefContains(String... value) {
        addContains(FavoriteColumns.SPDEF, value);
        return this;
    }

    public FavoriteSelection spdefStartsWith(String... value) {
        addStartsWith(FavoriteColumns.SPDEF, value);
        return this;
    }

    public FavoriteSelection spdefEndsWith(String... value) {
        addEndsWith(FavoriteColumns.SPDEF, value);
        return this;
    }

    public FavoriteSelection orderBySpdef(boolean desc) {
        orderBy(FavoriteColumns.SPDEF, desc);
        return this;
    }

    public FavoriteSelection orderBySpdef() {
        orderBy(FavoriteColumns.SPDEF, false);
        return this;
    }

    public FavoriteSelection weight(String... value) {
        addEquals(FavoriteColumns.WEIGHT, value);
        return this;
    }

    public FavoriteSelection weightNot(String... value) {
        addNotEquals(FavoriteColumns.WEIGHT, value);
        return this;
    }

    public FavoriteSelection weightLike(String... value) {
        addLike(FavoriteColumns.WEIGHT, value);
        return this;
    }

    public FavoriteSelection weightContains(String... value) {
        addContains(FavoriteColumns.WEIGHT, value);
        return this;
    }

    public FavoriteSelection weightStartsWith(String... value) {
        addStartsWith(FavoriteColumns.WEIGHT, value);
        return this;
    }

    public FavoriteSelection weightEndsWith(String... value) {
        addEndsWith(FavoriteColumns.WEIGHT, value);
        return this;
    }

    public FavoriteSelection orderByWeight(boolean desc) {
        orderBy(FavoriteColumns.WEIGHT, desc);
        return this;
    }

    public FavoriteSelection orderByWeight() {
        orderBy(FavoriteColumns.WEIGHT, false);
        return this;
    }

    public FavoriteSelection hp(String... value) {
        addEquals(FavoriteColumns.HP, value);
        return this;
    }

    public FavoriteSelection hpNot(String... value) {
        addNotEquals(FavoriteColumns.HP, value);
        return this;
    }

    public FavoriteSelection hpLike(String... value) {
        addLike(FavoriteColumns.HP, value);
        return this;
    }

    public FavoriteSelection hpContains(String... value) {
        addContains(FavoriteColumns.HP, value);
        return this;
    }

    public FavoriteSelection hpStartsWith(String... value) {
        addStartsWith(FavoriteColumns.HP, value);
        return this;
    }

    public FavoriteSelection hpEndsWith(String... value) {
        addEndsWith(FavoriteColumns.HP, value);
        return this;
    }

    public FavoriteSelection orderByHp(boolean desc) {
        orderBy(FavoriteColumns.HP, desc);
        return this;
    }

    public FavoriteSelection orderByHp() {
        orderBy(FavoriteColumns.HP, false);
        return this;
    }

    public FavoriteSelection created(String... value) {
        addEquals(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdNot(String... value) {
        addNotEquals(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdLike(String... value) {
        addLike(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdContains(String... value) {
        addContains(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdStartsWith(String... value) {
        addStartsWith(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection createdEndsWith(String... value) {
        addEndsWith(FavoriteColumns.CREATED, value);
        return this;
    }

    public FavoriteSelection orderByCreated(boolean desc) {
        orderBy(FavoriteColumns.CREATED, desc);
        return this;
    }

    public FavoriteSelection orderByCreated() {
        orderBy(FavoriteColumns.CREATED, false);
        return this;
    }

    public FavoriteSelection modified(String... value) {
        addEquals(FavoriteColumns.MODIFIED, value);
        return this;
    }

    public FavoriteSelection modifiedNot(String... value) {
        addNotEquals(FavoriteColumns.MODIFIED, value);
        return this;
    }

    public FavoriteSelection modifiedLike(String... value) {
        addLike(FavoriteColumns.MODIFIED, value);
        return this;
    }

    public FavoriteSelection modifiedContains(String... value) {
        addContains(FavoriteColumns.MODIFIED, value);
        return this;
    }

    public FavoriteSelection modifiedStartsWith(String... value) {
        addStartsWith(FavoriteColumns.MODIFIED, value);
        return this;
    }

    public FavoriteSelection modifiedEndsWith(String... value) {
        addEndsWith(FavoriteColumns.MODIFIED, value);
        return this;
    }

    public FavoriteSelection orderByModified(boolean desc) {
        orderBy(FavoriteColumns.MODIFIED, desc);
        return this;
    }

    public FavoriteSelection orderByModified() {
        orderBy(FavoriteColumns.MODIFIED, false);
        return this;
    }

    public FavoriteSelection types(String... value) {
        addEquals(FavoriteColumns.TYPES, value);
        return this;
    }

    public FavoriteSelection typesNot(String... value) {
        addNotEquals(FavoriteColumns.TYPES, value);
        return this;
    }

    public FavoriteSelection typesLike(String... value) {
        addLike(FavoriteColumns.TYPES, value);
        return this;
    }

    public FavoriteSelection typesContains(String... value) {
        addContains(FavoriteColumns.TYPES, value);
        return this;
    }

    public FavoriteSelection typesStartsWith(String... value) {
        addStartsWith(FavoriteColumns.TYPES, value);
        return this;
    }

    public FavoriteSelection typesEndsWith(String... value) {
        addEndsWith(FavoriteColumns.TYPES, value);
        return this;
    }

    public FavoriteSelection orderByTypes(boolean desc) {
        orderBy(FavoriteColumns.TYPES, desc);
        return this;
    }

    public FavoriteSelection orderByTypes() {
        orderBy(FavoriteColumns.TYPES, false);
        return this;
    }

    public FavoriteSelection image(String... value) {
        addEquals(FavoriteColumns.IMAGE, value);
        return this;
    }

    public FavoriteSelection imageNot(String... value) {
        addNotEquals(FavoriteColumns.IMAGE, value);
        return this;
    }

    public FavoriteSelection imageLike(String... value) {
        addLike(FavoriteColumns.IMAGE, value);
        return this;
    }

    public FavoriteSelection imageContains(String... value) {
        addContains(FavoriteColumns.IMAGE, value);
        return this;
    }

    public FavoriteSelection imageStartsWith(String... value) {
        addStartsWith(FavoriteColumns.IMAGE, value);
        return this;
    }

    public FavoriteSelection imageEndsWith(String... value) {
        addEndsWith(FavoriteColumns.IMAGE, value);
        return this;
    }

    public FavoriteSelection orderByImage(boolean desc) {
        orderBy(FavoriteColumns.IMAGE, desc);
        return this;
    }

    public FavoriteSelection orderByImage() {
        orderBy(FavoriteColumns.IMAGE, false);
        return this;
    }
}
