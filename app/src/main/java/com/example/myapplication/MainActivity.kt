package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyapplicationTheme
import androidx.compose.foundation.layout.Arrangement
import com.example.my_application.ui.theme.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Composable
//fun Home() {
//    Text(
//        modifier = Modifier
//            .background(Color.Red)
//            .padding(16.dp),
//        fontFamily = FontFamily.Monospace,
//        text = "hola desde mi home"
//    )
//    Home2()
//}
//
//@Composable
//fun Home2() {
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.SpaceEvenly
//        ) {
//            // Primera fila
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                    .background(Color.Gray),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(text = "1")
//                Text(text = "2")
//                Text(text = "3")
//            }
//
//            // Segunda fila
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                    .background(Color.LightGray).border(4.dp, Color.Black),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(text = "4")
//                Text(text = "5")
//                Text(text = "6")
//            }
//
//            // Tercera fila
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                    .background(Color.DarkGray),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(text = "7")
//                Text(text = "8")
//                Text(text = "9")
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_5)
//@Composable
//fun PreviewHome() {
//    Home()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyapplicationTheme {
//        Greeting("Android")
//    }
//}
//


