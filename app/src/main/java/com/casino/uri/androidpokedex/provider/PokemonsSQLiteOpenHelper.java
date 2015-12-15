package com.casino.uri.androidpokedex.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.casino.uri.androidpokedex.BuildConfig;
import com.casino.uri.androidpokedex.provider.pokemon.PokemonColumns;


public class PokemonsSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = PokemonsSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "pokemons.db";
    private static final int DATABASE_VERSION = 1;
    private static PokemonsSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final PokemonsSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_POKEMON = "CREATE TABLE IF NOT EXISTS "
            + PokemonColumns.TABLE_NAME + " ( "
            + PokemonColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PokemonColumns.NAME + " TEXT, "
            + PokemonColumns.PKDX_ID + " TEXT, "
            + PokemonColumns.HP + " INTEGER, "
            + PokemonColumns.SPATK + " INTEGER, "
            + PokemonColumns.SPDEF + " INTEGER, "
            + PokemonColumns.WEIGHT + " TEXT, "
            + PokemonColumns.CREATED + " TEXT, "
            + PokemonColumns.MODIFIED + " TEXT, "
            + PokemonColumns.TYPES + " TEXT "
            + " );";

    // @formatter:on

    public static PokemonsSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static PokemonsSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static PokemonsSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new PokemonsSQLiteOpenHelper(context);
    }

    private PokemonsSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new PokemonsSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static PokemonsSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new PokemonsSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PokemonsSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new PokemonsSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_POKEMON);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
