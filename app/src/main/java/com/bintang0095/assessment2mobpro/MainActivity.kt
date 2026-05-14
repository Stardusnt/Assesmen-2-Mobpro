package com.bintang0095.assessment2mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bintang0095.assessment2mobpro.navigation.NavGraph
import com.bintang0095.assessment2mobpro.ui.theme.Assessment2MobproTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContent {

            Assessment2MobproTheme {

                NavGraph()
            }
        }
    }
}