package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ItemsSection()
            FooterBar()
        }
    }
}

@Composable
fun FooterBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.DarkGray),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Opción 1", color = Color.White, fontSize = 16.sp)
        Text(text = "Opción 2", color = Color.White, fontSize = 16.sp)
        Text(text = "Opción 3", color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun ItemsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Item 1", color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Item 2", color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Item 3", color = Color.Black)
    }
}
