package com.bintang0095.assessment2mobpro.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bintang0095.assessment2mobpro.R
import com.bintang0095.assessment2mobpro.model.Habit
import com.bintang0095.assessment2mobpro.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainScreen(

    navController: NavController,

    viewModel: MainViewModel
) {

    val habitList by
    viewModel.habitList.collectAsState()

    val isGrid by
    viewModel.isGrid.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = stringResource(
                            R.string.title_habit
                        )
                    )
                },

                colors =
                    TopAppBarDefaults.topAppBarColors(

                        containerColor =
                            MaterialTheme.colorScheme.primary,

                        titleContentColor =
                            MaterialTheme.colorScheme.onPrimary
                    ),

                actions = {

                    IconButton(

                        onClick = {

                            viewModel.toggleLayout()
                        }

                    ) {

                        Icon(

                            painter = painterResource(

                                id =

                                    if (isGrid)

                                        R.drawable.baseline_view_list_24

                                    else

                                        R.drawable.baseline_grid_view_24
                            ),

                            contentDescription = null,

                            tint =
                                MaterialTheme
                                    .colorScheme
                                    .onPrimary
                        )
                    }
                }
            )
        },

        floatingActionButton = {

            FloatingActionButton(

                onClick = {

                    navController.navigate(

                        Screen.Detail.createRoute(

                            0,

                            "empty",

                            "empty",

                            false
                        )
                    )
                },

                containerColor =
                    MaterialTheme.colorScheme.primary,

                contentColor =
                    MaterialTheme.colorScheme.onPrimary
            ) {

                Icon(

                    imageVector =
                        Icons.Default.Add,

                    contentDescription = null
                )
            }
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // Gambar logo aplikasi di atas list
            Image(

                painter = painterResource(
                    id = R.drawable.habit_logo
                ),

                contentDescription = stringResource(
                    R.string.title_habit
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            if (habitList.isEmpty()) {

                Box(

                    modifier = Modifier
                        .fillMaxSize(),

                    contentAlignment = Alignment.Center
                ) {

                    Text(

                        text = stringResource(
                            R.string.empty_habit
                        ),

                        modifier =
                            Modifier.padding(16.dp)
                    )
                }

            } else {

                if (isGrid) {

                    LazyVerticalGrid(

                        columns =
                            GridCells.Fixed(2),

                        modifier = Modifier
                            .padding(8.dp),

                        contentPadding =
                            PaddingValues(
                                bottom = 100.dp
                            )
                    ) {

                        items(habitList) {

                            HabitItem(

                                habit = it,

                                navController =
                                    navController
                            )
                        }
                    }

                } else {

                    LazyColumn(

                        modifier = Modifier
                            .padding(8.dp),

                        contentPadding =
                            PaddingValues(
                                bottom = 100.dp
                            )
                    ) {

                        items(habitList) {

                            HabitItem(

                                habit = it,

                                navController =
                                    navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HabitItem(

    habit: Habit,

    navController: NavController
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        onClick = {

            navController.navigate(

                Screen.Detail.createRoute(

                    habit.id,

                    habit.title,

                    habit.category,

                    habit.isDone
                )
            )
        }

    ) {

        Column(

            modifier = Modifier
                .padding(16.dp)
        ) {

            Row(

                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                Icon(

                    imageVector = when (
                        habit.category
                    ) {

                        "Study",
                        "Belajar" ->

                            Icons.Default.School

                        "Workout",
                        "Olahraga" ->

                            Icons.Default.FitnessCenter

                        "Reading",
                        "Membaca" ->

                            Icons.Default.MenuBook

                        else ->

                            Icons.Default.HealthAndSafety
                    },

                    contentDescription = null,

                    tint =
                        MaterialTheme
                            .colorScheme
                            .primary
                )

                Text(

                    text = habit.title,

                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
                )
            }

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            Text(
                text = habit.category
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            Row(

                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                Icon(

                    imageVector =
                        Icons.Default.CheckCircle,

                    contentDescription = null,

                    tint =

                        if (habit.isDone)

                            MaterialTheme
                                .colorScheme
                                .primary

                        else

                            MaterialTheme
                                .colorScheme
                                .error
                )

                Text(

                    text =

                        if (habit.isDone)

                            stringResource(
                                R.string.completed
                            )

                        else

                            stringResource(
                                R.string.not_completed
                            )
                )
            }
        }
    }
}