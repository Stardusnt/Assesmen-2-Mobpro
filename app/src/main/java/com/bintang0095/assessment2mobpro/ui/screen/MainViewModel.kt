package com.bintang0095.assessment2mobpro.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bintang0095.assessment2mobpro.database.HabitDao
import com.bintang0095.assessment2mobpro.model.Habit
import com.bintang0095.assessment2mobpro.util.SettingsDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val dao: HabitDao,
    private val settingsDataStore: SettingsDataStore
) : ViewModel() {

    private val _habitList =
        MutableStateFlow<List<Habit>>(
            emptyList()
        )

    val habitList:
            StateFlow<List<Habit>>
            = _habitList

    private val _isGrid =
        MutableStateFlow(true)

    val isGrid: StateFlow<Boolean>
            = _isGrid

    init {

        getAllHabit()

        viewModelScope.launch {

            settingsDataStore.layoutFlow.collect {

                _isGrid.value = it
            }
        }
    }

    private fun getAllHabit() {

        viewModelScope.launch {

            dao.getAllHabit().collect {

                _habitList.value = it
            }
        }
    }

    fun toggleLayout() {

        viewModelScope.launch {

            val newValue = !_isGrid.value

            settingsDataStore.saveLayout(newValue)

            _isGrid.value = newValue
        }
    }

    fun insertHabit(
        habit: Habit
    ) {

        viewModelScope.launch {

            dao.insertHabit(habit)
        }
    }

    fun updateHabit(
        habit: Habit
    ) {

        viewModelScope.launch {

            dao.updateHabit(habit)
        }
    }

    fun deleteHabit(
        habit: Habit
    ) {

        viewModelScope.launch {

            dao.deleteHabit(habit)
        }
    }
}