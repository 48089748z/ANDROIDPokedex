package com.casino.uri.androidpokedex.provider.favorite;

import com.casino.uri.androidpokedex.provider.base.BaseModel;

import android.support.annotation.Nullable;

/**
 * Data model for the {@code favorite} table.
 */
public interface FavoriteModel extends BaseModel {

    /**
     * Get the {@code pkdx_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPkdxId();

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Get the {@code spatk} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpatk();

    /**
     * Get the {@code spdef} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSpdef();

    /**
     * Get the {@code weight} value.
     * Can be {@code null}.
     */
    @Nullable
    String getWeight();

    /**
     * Get the {@code hp} value.
     * Can be {@code null}.
     */
    @Nullable
    String getHp();

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

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImage();
}
