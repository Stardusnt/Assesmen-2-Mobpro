package com.bintang0095.assessment2mobpro.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by
preferencesDataStore(
    name = "settings"
)

class SettingsDataStore(
    private val context: Context
) {

    companion object {

        val GRID_KEY =
            booleanPreferencesKey(
                "grid_mode"
            )
    }

    val layoutFlow =
        context.dataStore.data.map {

            it[GRID_KEY] ?: true
        }

    suspend fun saveLayout(
        isGrid: Boolean
    ) {

        context.dataStore.edit {

            it[GRID_KEY] = isGrid
        }
    }
}