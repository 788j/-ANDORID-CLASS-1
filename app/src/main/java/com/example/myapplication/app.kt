@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.my_application.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// --------------------------------------------------
// ESTRUCTURA DE DATOS: Producto con ID y Descripción
data class Producto(
    val id: Int,
    val descripcion: String
)

// --------------------------------------------------
// 🆕 LISTA DE PRODUCTOS MOVIDA AL AMBITO SUPERIOR para ser accesible por DetailsScreen
val productos = listOf(
    Producto(101, "Silla ergonómica de oficina y malla transpirable"),
    Producto(102, "Mesa de centro moderna con tapa de cristal"),
    Producto(103, "Lámpara de pie ajustable de diseño minimalista"),
    Producto(104, "Sofá modular de tres puestos en color gris oscuro"),
    Producto(105, "Estantería flotante de madera de roble macizo"),
    Producto(106, "Espejo de pared con marco metálico negro")
)
// --------------------------------------------------

// Definición de pantallas (Sin cambios)
sealed class Screen(val route: String) {
    data object Home : Screen(route = "home")
    data object Details : Screen(route = "details/{id}") {
        fun createRoute(id: Int) = "details/$id"
        const val arg = "id"
    }
    data object Settings : Screen(route = "settings")
}

// --------------------------------------------------
// Comienzo del app (Sin cambios)
@Composable
fun App() {
    val nav = rememberNavController()
    AppNavGraph(navController = nav)
}

// --------------------------------------------------
// Gráfico de navegación (Sin cambios)
@SuppressLint("Listado de FrameWorks para NODE")
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
                // Le pasamos la lista de productos para que pueda buscar la descripción
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
// HomeScreen (Sin cambios, usa la lista global)
@Composable
fun HomeScreen(
    onNavigationToDetails: (Int) -> Unit,
    onNavigationToSettings: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Text(text = "Home (Galería de Productos)", modifier = Modifier.padding(46.dp)) }
    ) { padding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Haz clic en la imagen para ver los detalles del producto:"
            )

            // --- GALERÍA DE IMÁGENES HORIZONTAL ---
            LazyRow(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(productos) { producto ->
                    Column(

                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .size(150.dp, 200.dp)
                            .background(Color.LightGray)
                            .clickable { onNavigationToDetails(producto.id) }
                    ) {
                        Text(
                            text = "ID: ${producto.id}",
                            modifier = Modifier.padding(top = 16.dp),
                            color = Color.Black
                        )
                       /* Text(
                            text = producto.descripcion.take(20) + "...", // Descripción cortada
                            modifier = Modifier.padding(horizontal = 8.dp),
                            color = Color.DarkGray
                        )*/
                    }
                }
            }
            // --- FIN GALERÍA ---

         /*   Button(
                onClick = onNavigationToSettings,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Ir a Settings")
            }*/
        }
    }
}

// --------------------------------------------------
// 🚀 DetailsScreen MODIFICADA: Busca y muestra la descripción
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(id: Int, onBack: () -> Unit) {
    // 🆕 BUSCAMOS EL PRODUCTO en la lista global
    val productoEncontrado = productos.find { it.id == id }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalle del Producto") },
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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Producto con ID $id",
                style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
            )

            productoEncontrado?.let { producto ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .size(250.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Imagen ampliada", color = Color.White)
                }

                // 🆕 MOSTRAMOS LA DESCRIPCIÓN
                Text(
                    text = "Descripción:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = producto.descripcion,
                    modifier = Modifier.fillMaxWidth()
                )
            } ?: Text("Error: Producto no encontrado.", color = Color.Red)


            Button(
                onClick = { onBack() },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "Volver")
            }
        }
    }
}

// --------------------------------------------------
// SettingsScreen (Sin cambios)
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