package com.bintang0095.assessment2mobpro.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bintang0095.assessment2mobpro.database.HabitDao
import com.bintang0095.assessment2mobpro.ui.screen.MainViewModel

class ViewModelFactory(
    private val dao: HabitDao,
    private val settingsDataStore: SettingsDataStore
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel>
            create(
        modelClass: Class<T>
    ): T {

        if (

            modelClass.isAssignableFrom(
                MainViewModel::class.java
            )

        ) {

            return MainViewModel(
                dao,
                settingsDataStore
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel"
        )
    }
}