package com.example.my_application.ui.theme.data // ⬅️ CAMBIO DE PAQUETE

import com.example.myapplication.R


// ESTRUCTURA
data class Producto(
    val id: Int,
    val descripcion: String,
    val imageUrl: Int
)

// LISTA
val productos = listOf(

    Producto(101, "Silla ergonómica de oficina y malla transpirable", R.drawable.silla),
    Producto(102, "Mesa de centro moderna con tapa de cristal", R.drawable.msea),
    Producto(103, "Lámpara de pie ajustable de diseño minimalista", R.drawable.lampara),
    Producto(104, "Sofá modular de tres puestos en color gris oscuro", R.drawable.sofa),
    Producto(105, "Estantería flotante de madera de roble macizo", R.drawable.estanteria),
    Producto(106, "Espejo de pared con marco metálico negro", R.drawable.espejo)
)