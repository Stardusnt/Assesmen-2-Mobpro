package com.bintang0095.assessment2mobpro.navigation

sealed class Screen(
    val route: String
) {

    data object Main :
        Screen("main")

    data object Detail :
        Screen(
            "detail/{id}/{title}/{category}/{done}"
        ) {

        fun createRoute(
            id: Int,
            title: String,
            category: String,
            done: Boolean
        ): String {

            return "detail/$id/$title/$category/$done"
        }
    }
}