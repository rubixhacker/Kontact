package com.hackedcube.kontact

import android.database.Cursor

fun Boolean.toFlag(): Int {
    val flag = when (this) {
        true -> 1
        false -> 0
    }

    return flag
}

fun Cursor.toSequence(): Sequence<Cursor> {
    return if (this.moveToNext()) {
        generateSequence(this, { if (this.moveToNext()) this else null })
    } else {
        emptySequence()
    }
}
