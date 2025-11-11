@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.my_application.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// --------------------------------------------------
// Definición de pantallas
sealed class Screen(val route: String) {
    data object Home : Screen(route = "home")
    data object Details : Screen(route = "details/{id}") {
        fun createRoute(id: Int) = "details/$id"
        const val arg = "id"
    }
    data object Settings : Screen(route = "settings")
}

// --------------------------------------------------
// Comienzo del app
@Composable
fun App() {
    val nav = rememberNavController()
    AppNavGraph(navController = nav)
}

// --------------------------------------------------
// Gráfico de navegación
@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // 🏠 Pantalla Home
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigationToDetails = { id ->
                    navController.navigate(Screen.Details.createRoute(id))
                },
                onNavigationToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        // 📄 Pantalla Details
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(name = Screen.Details.arg) {
                type = NavType.IntType
            })
        ) { stackEntry ->
            val id = stackEntry.arguments?.getInt(Screen.Details.arg) ?: -1
            DetailsScreen(
                id = id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // ⚙️ Pantalla Settings
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

// --------------------------------------------------
// HomeScreen
@Composable
fun HomeScreen(
    onNavigationToDetails: (Int) -> Unit,
    onNavigationToSettings: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Text(text = "Home") }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Pantalla Home de Navigation Compose"
            )
            LazyRow{
                item {
                    Row(modifier = Modifier.padding(8.dp)) {
                        listOf(1, 2, 3, 4, 5, 6, 7).forEach { id ->
                            AssistChip(
                                onClick = { onNavigationToDetails(id) },
                                label = { Text(text = "Details $id") }
                            )
                        }
                    }
                }
            }

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onNavigationToSettings() },
                text = "Ir a Settings"
            )
        }
    }
}

// --------------------------------------------------
// DetailsScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(id: Int, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Details") },
                navigationIcon = {
                    Text(
                        modifier = Modifier.clickable { onBack() },
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
                modifier = Modifier.padding(all = 16.dp),
                text = "Detalles del cliente con el ID $id"
            )
            Button(onClick = { onBack() }) {
                Text(text = "Volver")
            }
        }
    }
}

// --------------------------------------------------
// SettingsScreen
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                navigationIcon = {
                    Text(
                        modifier = Modifier.clickable { onBack() },
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



