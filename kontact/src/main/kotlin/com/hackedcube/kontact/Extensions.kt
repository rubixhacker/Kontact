package com.hackedcube.kontact

fun Boolean.toFlag(): Int {
    val flag = when (this) {
        true -> 1
        false -> 0
    }

    return flag
}
