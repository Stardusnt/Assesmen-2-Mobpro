package com.bintang0095.assessment2mobpro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bintang0095.assessment2mobpro.database.HabitDb
import com.bintang0095.assessment2mobpro.ui.screen.DetailScreen
import com.bintang0095.assessment2mobpro.ui.screen.MainScreen
import com.bintang0095.assessment2mobpro.ui.screen.MainViewModel
import com.bintang0095.assessment2mobpro.util.SettingsDataStore
import com.bintang0095.assessment2mobpro.util.ViewModelFactory

@Composable
fun NavGraph() {

    val navController =
        rememberNavController()

    val context =
        LocalContext.current

    val db =
        HabitDb.getInstance(context)

    val factory =
        ViewModelFactory(
            db.habitDao(),
            SettingsDataStore(context)  // tambah ini
        )

    val viewModel: MainViewModel =
        viewModel(factory = factory)

    NavHost(

        navController = navController,

        startDestination =
            Screen.Main.route
    ) {

        composable(
            route = Screen.Main.route
        ) {

            MainScreen(

                navController =
                    navController,

                viewModel =
                    viewModel
            )
        }

        composable(

            route =
                Screen.Detail.route,

            arguments = listOf(

                navArgument("id") {

                    type =
                        NavType.IntType
                },

                navArgument("title") {

                    type =
                        NavType.StringType
                },

                navArgument("category") {

                    type =
                        NavType.StringType
                },

                navArgument("done") {

                    type =
                        NavType.BoolType
                }
            )

        ) {

            DetailScreen(

                navController =
                    navController,

                viewModel =
                    viewModel,

                id =
                    it.arguments?.getInt("id")
                        ?: 0,

                title =
                    it.arguments?.getString("title")
                        ?: "",

                category =
                    it.arguments?.getString("category")
                        ?: "",

                done =
                    it.arguments?.getBoolean("done")
                        ?: false
            )
        }
    }
}