@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.my_application.ui.theme


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.navigation.AppNavGraph


// --------------------------------------------------
//  app
@Composable
fun App() {
    val nav = rememberNavController()
    AppNavGraph(navController = nav)
}



// SettingsScreen (Deplecada para el ejercicio del profe)
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                navigationIcon = {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable { onBack() },
                        text = "⬅"
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Pantalla de configuración"
            )
        }
    }
}
*/