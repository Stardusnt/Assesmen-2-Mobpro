package com.bintang0095.assessment2mobpro.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bintang0095.assessment2mobpro.R
import com.bintang0095.assessment2mobpro.model.Habit

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun DetailScreen(

    navController: NavController,

    viewModel: MainViewModel,

    id: Int,

    title: String,

    category: String,

    done: Boolean
) {

    var habitTitle by remember {

        mutableStateOf(

            if (title == "empty")
                ""
            else
                title
        )
    }

    var habitCategory by remember {

        mutableStateOf(

            if (category == "empty")
                ""
            else
                category
        )
    }

    var isDone by remember {

        mutableStateOf(done)
    }

    var expandedMenu by remember {

        mutableStateOf(false)
    }

    var showDialog by remember {

        mutableStateOf(false)
    }

    var expandedDropdown by remember {

        mutableStateOf(false)
    }

    val pleaseFill =
        stringResource(R.string.please_fill)

    val successMessage =
        stringResource(R.string.success)

    val categoryList = listOf(

        stringResource(R.string.study),

        stringResource(R.string.workout),

        stringResource(R.string.reading),

        stringResource(R.string.health)
    )

    if (showDialog) {

        DisplayAlertDialog(

            onDismissRequest = {

                showDialog = false
            },

            onConfirmation = {

                viewModel.deleteHabit(

                    Habit(

                        id = id,

                        title = habitTitle,

                        category = habitCategory,

                        isDone = isDone
                    )
                )

                showDialog = false

                navController.popBackStack()
            }
        )
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(

                        text =

                            if (id == 0)

                                stringResource(
                                    R.string.add_habit
                                )

                            else

                                stringResource(
                                    R.string.edit_habit
                                )
                    )
                },

                navigationIcon = {

                    IconButton(

                        onClick = {

                            navController.popBackStack()
                        }

                    ) {

                        Icon(

                            imageVector =
                                Icons.Default.ArrowBack,

                            contentDescription =
                                stringResource(
                                    R.string.back
                                ),

                            tint =
                                MaterialTheme
                                    .colorScheme
                                    .onPrimary
                        )
                    }
                },

                actions = {

                    if (id != 0) {

                        IconButton(

                            onClick = {

                                expandedMenu = true
                            }

                        ) {

                            Icon(

                                imageVector =
                                    Icons.Default.MoreVert,

                                contentDescription =
                                    stringResource(
                                        R.string.other
                                    ),

                                tint =
                                    MaterialTheme
                                        .colorScheme
                                        .onPrimary
                            )
                        }

                        DropdownMenu(

                            expanded = expandedMenu,

                            onDismissRequest = {

                                expandedMenu = false
                            }

                        ) {

                            DropdownMenuItem(

                                text = {

                                    Text(

                                        text =
                                            stringResource(
                                                R.string.delete
                                            )
                                    )
                                },

                                leadingIcon = {

                                    Icon(

                                        imageVector =
                                            Icons.Default.Delete,

                                        contentDescription = null
                                    )
                                },

                                onClick = {

                                    expandedMenu = false

                                    showDialog = true
                                }
                            )
                        }
                    }
                },

                colors =
                    TopAppBarDefaults.topAppBarColors(

                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .primary,

                        titleContentColor =
                            MaterialTheme
                                .colorScheme
                                .onPrimary
                    )
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            OutlinedTextField(

                value = habitTitle,

                onValueChange = {

                    habitTitle = it
                },

                modifier =
                    Modifier.fillMaxWidth(),

                leadingIcon = {

                    Icon(

                        imageVector =
                            Icons.Default.Title,

                        contentDescription = null
                    )
                },

                label = {

                    Text(

                        text =
                            stringResource(
                                R.string.habit_title
                            )
                    )
                }
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            ExposedDropdownMenuBox(

                expanded = expandedDropdown,

                onExpandedChange = {

                    expandedDropdown =
                        !expandedDropdown
                }

            ) {

                OutlinedTextField(

                    value = habitCategory,

                    onValueChange = {},

                    readOnly = true,

                    modifier = Modifier
                        .menuAnchor()          // FIX 1: tambah menuAnchor()
                        .fillMaxWidth(),

                    leadingIcon = {

                        Icon(

                            imageVector =
                                Icons.Default.Category,

                            contentDescription = null
                        )
                    },

                    label = {

                        Text(

                            text =
                                stringResource(
                                    R.string.category
                                )
                        )
                    },

                    trailingIcon = {

                        ExposedDropdownMenuDefaults
                            .TrailingIcon(

                                expanded =
                                    expandedDropdown
                            )
                    }
                )

                ExposedDropdownMenu(           // FIX 2: ganti DropdownMenu -> ExposedDropdownMenu

                    expanded = expandedDropdown,

                    onDismissRequest = {

                        expandedDropdown =
                            false
                    }

                ) {

                    categoryList.forEach {

                        DropdownMenuItem(

                            text = {

                                Text(it)
                            },

                            onClick = {

                                habitCategory = it

                                expandedDropdown = false
                            }
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            Row {

                Checkbox(

                    checked = isDone,

                    onCheckedChange = {

                        isDone = it
                    }
                )

                Text(

                    text =
                        stringResource(
                            R.string.completed
                        ),

                    modifier =
                        Modifier.padding(
                            top = 12.dp
                        )
                )
            }

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            Button(

                onClick = {

                    if (

                        habitTitle.isEmpty() ||

                        habitCategory.isEmpty()
                    ) {

                        Toast.makeText(

                            navController.context,

                            pleaseFill,

                            Toast.LENGTH_SHORT

                        ).show()

                    } else {

                        if (id == 0) {

                            viewModel.insertHabit(

                                Habit(

                                    title = habitTitle,

                                    category = habitCategory,

                                    isDone = isDone
                                )
                            )

                        } else {

                            viewModel.updateHabit(

                                Habit(

                                    id = id,

                                    title = habitTitle,

                                    category = habitCategory,

                                    isDone = isDone
                                )
                            )
                        }

                        Toast.makeText(

                            navController.context,

                            successMessage,

                            Toast.LENGTH_SHORT

                        ).show()

                        navController.popBackStack()
                    }
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(

                    text =
                        stringResource(
                            R.string.save
                        )
                )
            }
        }
    }
}