package com.casino.uri.androidpokedex.provider.pokemon;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.casino.uri.androidpokedex.provider.base.AbstractSelection;


/**
 * Selection for the {@code pokemon} table.
 */
public class PokemonSelection extends AbstractSelection<PokemonSelection> {
    @Override
    protected Uri baseUri() {
        return PokemonColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PokemonCursor} object, which is positioned before the first entry, or null.
     */
    public PokemonCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PokemonCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public PokemonCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code PokemonCursor} object, which is positioned before the first entry, or null.
     */
    public PokemonCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new PokemonCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public PokemonCursor query(Context context) {
        return query(context, null);
    }


    public PokemonSelection id(long... value) {
        addEquals("pokemon." + PokemonColumns._ID, toObjectArray(value));
        return this;
    }

    public PokemonSelection idNot(long... value) {
        addNotEquals("pokemon." + PokemonColumns._ID, toObjectArray(value));
        return this;
    }

    public PokemonSelection orderById(boolean desc) {
        orderBy("pokemon." + PokemonColumns._ID, desc);
        return this;
    }

    public PokemonSelection orderById() {
        return orderById(false);
    }

    public PokemonSelection name(String... value) {
        addEquals(PokemonColumns.NAME, value);
        return this;
    }

    public PokemonSelection nameNot(String... value) {
        addNotEquals(PokemonColumns.NAME, value);
        return this;
    }

    public PokemonSelection nameLike(String... value) {
        addLike(PokemonColumns.NAME, value);
        return this;
    }

    public PokemonSelection nameContains(String... value) {
        addContains(PokemonColumns.NAME, value);
        return this;
    }

    public PokemonSelection nameStartsWith(String... value) {
        addStartsWith(PokemonColumns.NAME, value);
        return this;
    }

    public PokemonSelection nameEndsWith(String... value) {
        addEndsWith(PokemonColumns.NAME, value);
        return this;
    }

    public PokemonSelection orderByName(boolean desc) {
        orderBy(PokemonColumns.NAME, desc);
        return this;
    }

    public PokemonSelection orderByName() {
        orderBy(PokemonColumns.NAME, false);
        return this;
    }

    public PokemonSelection pkdxId(String... value) {
        addEquals(PokemonColumns.PKDX_ID, value);
        return this;
    }

    public PokemonSelection pkdxIdNot(String... value) {
        addNotEquals(PokemonColumns.PKDX_ID, value);
        return this;
    }

    public PokemonSelection pkdxIdLike(String... value) {
        addLike(PokemonColumns.PKDX_ID, value);
        return this;
    }

    public PokemonSelection pkdxIdContains(String... value) {
        addContains(PokemonColumns.PKDX_ID, value);
        return this;
    }

    public PokemonSelection pkdxIdStartsWith(String... value) {
        addStartsWith(PokemonColumns.PKDX_ID, value);
        return this;
    }

    public PokemonSelection pkdxIdEndsWith(String... value) {
        addEndsWith(PokemonColumns.PKDX_ID, value);
        return this;
    }

    public PokemonSelection orderByPkdxId(boolean desc) {
        orderBy(PokemonColumns.PKDX_ID, desc);
        return this;
    }

    public PokemonSelection orderByPkdxId() {
        orderBy(PokemonColumns.PKDX_ID, false);
        return this;
    }

    public PokemonSelection hp(Integer... value) {
        addEquals(PokemonColumns.HP, value);
        return this;
    }

    public PokemonSelection hpNot(Integer... value) {
        addNotEquals(PokemonColumns.HP, value);
        return this;
    }

    public PokemonSelection hpGt(int value) {
        addGreaterThan(PokemonColumns.HP, value);
        return this;
    }

    public PokemonSelection hpGtEq(int value) {
        addGreaterThanOrEquals(PokemonColumns.HP, value);
        return this;
    }

    public PokemonSelection hpLt(int value) {
        addLessThan(PokemonColumns.HP, value);
        return this;
    }

    public PokemonSelection hpLtEq(int value) {
        addLessThanOrEquals(PokemonColumns.HP, value);
        return this;
    }

    public PokemonSelection orderByHp(boolean desc) {
        orderBy(PokemonColumns.HP, desc);
        return this;
    }

    public PokemonSelection orderByHp() {
        orderBy(PokemonColumns.HP, false);
        return this;
    }

    public PokemonSelection spatk(Integer... value) {
        addEquals(PokemonColumns.SPATK, value);
        return this;
    }

    public PokemonSelection spatkNot(Integer... value) {
        addNotEquals(PokemonColumns.SPATK, value);
        return this;
    }

    public PokemonSelection spatkGt(int value) {
        addGreaterThan(PokemonColumns.SPATK, value);
        return this;
    }

    public PokemonSelection spatkGtEq(int value) {
        addGreaterThanOrEquals(PokemonColumns.SPATK, value);
        return this;
    }

    public PokemonSelection spatkLt(int value) {
        addLessThan(PokemonColumns.SPATK, value);
        return this;
    }

    public PokemonSelection spatkLtEq(int value) {
        addLessThanOrEquals(PokemonColumns.SPATK, value);
        return this;
    }

    public PokemonSelection orderBySpatk(boolean desc) {
        orderBy(PokemonColumns.SPATK, desc);
        return this;
    }

    public PokemonSelection orderBySpatk() {
        orderBy(PokemonColumns.SPATK, false);
        return this;
    }

    public PokemonSelection spdef(Integer... value) {
        addEquals(PokemonColumns.SPDEF, value);
        return this;
    }

    public PokemonSelection spdefNot(Integer... value) {
        addNotEquals(PokemonColumns.SPDEF, value);
        return this;
    }

    public PokemonSelection spdefGt(int value) {
        addGreaterThan(PokemonColumns.SPDEF, value);
        return this;
    }

    public PokemonSelection spdefGtEq(int value) {
        addGreaterThanOrEquals(PokemonColumns.SPDEF, value);
        return this;
    }

    public PokemonSelection spdefLt(int value) {
        addLessThan(PokemonColumns.SPDEF, value);
        return this;
    }

    public PokemonSelection spdefLtEq(int value) {
        addLessThanOrEquals(PokemonColumns.SPDEF, value);
        return this;
    }

    public PokemonSelection orderBySpdef(boolean desc) {
        orderBy(PokemonColumns.SPDEF, desc);
        return this;
    }

    public PokemonSelection orderBySpdef() {
        orderBy(PokemonColumns.SPDEF, false);
        return this;
    }

    public PokemonSelection weight(String... value) {
        addEquals(PokemonColumns.WEIGHT, value);
        return this;
    }

    public PokemonSelection weightNot(String... value) {
        addNotEquals(PokemonColumns.WEIGHT, value);
        return this;
    }

    public PokemonSelection weightLike(String... value) {
        addLike(PokemonColumns.WEIGHT, value);
        return this;
    }

    public PokemonSelection weightContains(String... value) {
        addContains(PokemonColumns.WEIGHT, value);
        return this;
    }

    public PokemonSelection weightStartsWith(String... value) {
        addStartsWith(PokemonColumns.WEIGHT, value);
        return this;
    }

    public PokemonSelection weightEndsWith(String... value) {
        addEndsWith(PokemonColumns.WEIGHT, value);
        return this;
    }

    public PokemonSelection orderByWeight(boolean desc) {
        orderBy(PokemonColumns.WEIGHT, desc);
        return this;
    }

    public PokemonSelection orderByWeight() {
        orderBy(PokemonColumns.WEIGHT, false);
        return this;
    }

    public PokemonSelection created(String... value) {
        addEquals(PokemonColumns.CREATED, value);
        return this;
    }

    public PokemonSelection createdNot(String... value) {
        addNotEquals(PokemonColumns.CREATED, value);
        return this;
    }

    public PokemonSelection createdLike(String... value) {
        addLike(PokemonColumns.CREATED, value);
        return this;
    }

    public PokemonSelection createdContains(String... value) {
        addContains(PokemonColumns.CREATED, value);
        return this;
    }

    public PokemonSelection createdStartsWith(String... value) {
        addStartsWith(PokemonColumns.CREATED, value);
        return this;
    }

    public PokemonSelection createdEndsWith(String... value) {
        addEndsWith(PokemonColumns.CREATED, value);
        return this;
    }

    public PokemonSelection orderByCreated(boolean desc) {
        orderBy(PokemonColumns.CREATED, desc);
        return this;
    }

    public PokemonSelection orderByCreated() {
        orderBy(PokemonColumns.CREATED, false);
        return this;
    }

    public PokemonSelection modified(String... value) {
        addEquals(PokemonColumns.MODIFIED, value);
        return this;
    }

    public PokemonSelection modifiedNot(String... value) {
        addNotEquals(PokemonColumns.MODIFIED, value);
        return this;
    }

    public PokemonSelection modifiedLike(String... value) {
        addLike(PokemonColumns.MODIFIED, value);
        return this;
    }

    public PokemonSelection modifiedContains(String... value) {
        addContains(PokemonColumns.MODIFIED, value);
        return this;
    }

    public PokemonSelection modifiedStartsWith(String... value) {
        addStartsWith(PokemonColumns.MODIFIED, value);
        return this;
    }

    public PokemonSelection modifiedEndsWith(String... value) {
        addEndsWith(PokemonColumns.MODIFIED, value);
        return this;
    }

    public PokemonSelection orderByModified(boolean desc) {
        orderBy(PokemonColumns.MODIFIED, desc);
        return this;
    }

    public PokemonSelection orderByModified() {
        orderBy(PokemonColumns.MODIFIED, false);
        return this;
    }

    public PokemonSelection types(String... value) {
        addEquals(PokemonColumns.TYPES, value);
        return this;
    }

    public PokemonSelection typesNot(String... value) {
        addNotEquals(PokemonColumns.TYPES, value);
        return this;
    }

    public PokemonSelection typesLike(String... value) {
        addLike(PokemonColumns.TYPES, value);
        return this;
    }

    public PokemonSelection typesContains(String... value) {
        addContains(PokemonColumns.TYPES, value);
        return this;
    }

    public PokemonSelection typesStartsWith(String... value) {
        addStartsWith(PokemonColumns.TYPES, value);
        return this;
    }

    public PokemonSelection typesEndsWith(String... value) {
        addEndsWith(PokemonColumns.TYPES, value);
        return this;
    }

    public PokemonSelection orderByTypes(boolean desc) {
        orderBy(PokemonColumns.TYPES, desc);
        return this;
    }

    public PokemonSelection orderByTypes() {
        orderBy(PokemonColumns.TYPES, false);
        return this;
    }
}
