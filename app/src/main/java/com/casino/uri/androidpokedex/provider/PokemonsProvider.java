package com.casino.uri.androidpokedex.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.casino.uri.androidpokedex.BuildConfig;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;

import com.casino.uri.androidpokedex.provider.base.BaseContentProvider;
import com.casino.uri.androidpokedex.provider.favorite.FavoriteColumns;

public class PokemonsProvider extends BaseContentProvider {
    private static final String TAG = PokemonsProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.pokemons.app.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_FAVORITE = 0;
    private static final int URI_TYPE_FAVORITE_ID = 1;

    private static final int URI_TYPE_POKEMON = 2;
    private static final int URI_TYPE_POKEMON_ID = 3;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, FavoriteColumns.TABLE_NAME, URI_TYPE_FAVORITE);
        URI_MATCHER.addURI(AUTHORITY, FavoriteColumns.TABLE_NAME + "/#", URI_TYPE_FAVORITE_ID);
        URI_MATCHER.addURI(AUTHORITY, PokemonColumns.TABLE_NAME, URI_TYPE_POKEMON);
        URI_MATCHER.addURI(AUTHORITY, PokemonColumns.TABLE_NAME + "/#", URI_TYPE_POKEMON_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return PokemonsSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_FAVORITE:
                return TYPE_CURSOR_DIR + FavoriteColumns.TABLE_NAME;
            case URI_TYPE_FAVORITE_ID:
                return TYPE_CURSOR_ITEM + FavoriteColumns.TABLE_NAME;

            case URI_TYPE_POKEMON:
                return TYPE_CURSOR_DIR + PokemonColumns.TABLE_NAME;
            case URI_TYPE_POKEMON_ID:
                return TYPE_CURSOR_ITEM + PokemonColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_FAVORITE:
            case URI_TYPE_FAVORITE_ID:
                res.table = FavoriteColumns.TABLE_NAME;
                res.idColumn = FavoriteColumns._ID;
                res.tablesWithJoins = FavoriteColumns.TABLE_NAME;
                res.orderBy = FavoriteColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_POKEMON:
            case URI_TYPE_POKEMON_ID:
                res.table = PokemonColumns.TABLE_NAME;
                res.idColumn = PokemonColumns._ID;
                res.tablesWithJoins = PokemonColumns.TABLE_NAME;
                res.orderBy = PokemonColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_FAVORITE_ID:
            case URI_TYPE_POKEMON_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
