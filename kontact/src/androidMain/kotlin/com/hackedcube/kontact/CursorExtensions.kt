package com.hackedcube.kontact

import android.database.Cursor

internal fun Cursor.getString(columnName: String): String {
    return getString(getColumnIndexOrThrow(columnName))
}

internal fun Cursor.getStringOrNull(columnName: String): String? {
    val index = getColumnIndex(columnName)
    return if (index == -1 || isNull(index)) null else getString(index)
}

internal fun Cursor.getInt(columnName: String): Int {
    return getInt(getColumnIndexOrThrow(columnName))
}

internal fun Cursor.getIntOrNull(columnName: String): Int? {
    val index = getColumnIndex(columnName)
    return if (index == -1 || isNull(index)) null else getInt(index)
}

internal fun Cursor.getLong(columnName: String): Long {
    return getLong(getColumnIndexOrThrow(columnName))
}

internal fun Cursor.getLongOrNull(columnName: String): Long? {
    val index = getColumnIndex(columnName)
    return if (index == -1 || isNull(index)) null else getLong(index)
}

internal fun Cursor.asSequence(): Sequence<Cursor> = generateSequence {
    if (moveToNext()) this else null
}
