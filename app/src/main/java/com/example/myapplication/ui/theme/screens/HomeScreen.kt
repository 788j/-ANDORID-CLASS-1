package com.example.myapplication.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my_application.ui.theme.data.productos

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
                text = "Haz clic en la imagen para ver los detalles del producto:",
                color = Color(0xFF006400)
            )

            // GALERÍA
            LazyRow(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(productos) { producto ->
                    Box(
                        modifier = Modifier
                            .size(150.dp, 200.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .clickable { onNavigationToDetails(producto.id) }
                    ){
                        //  imagen producto
                        Image(
                            painter = painterResource(id = producto.imageUrl),
                            contentDescription = "Imagen de ${producto.descripcion}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        //  etiqueta ID
                        Text(
                            text = "ID: ${producto.id}",

                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .background(
                                    Color.Black.copy(alpha = 0.6f),
                                    shape = RoundedCornerShape(topEnd = 8.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            // --- boton settings elimanaod para este ejercicio---

            /*   Button(
                   onClick = onNavigationToSettings,
                   modifier = Modifier.padding(16.dp)
               ) {
                   Text(text = "Ir a Settings")
               }*/
        }
    }
}