package com.casino.uri.androidpokedex.provider.pokemon;


import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.casino.uri.androidpokedex.provider.base.BaseModel;

/**
 * A Pokemon.
 */
public interface PokemonModel extends BaseModel {

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Get the {@code pkdx_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPkdxId();

    /**
     * Get the {@code hp} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getHp();

    /**
     * Get the {@code spatk} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getSpatk();

    /**
     * Get the {@code spdef} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getSpdef();

    /**
     * Get the {@code weight} value.
     * Can be {@code null}.
     */
    @Nullable
    String getWeight();

    /**
     * Get the {@code created} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCreated();

    /**
     * Get the {@code modified} value.
     * Can be {@code null}.
     */
    @Nullable
    String getModified();

    /**
     * Get the {@code types} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTypes();
}
