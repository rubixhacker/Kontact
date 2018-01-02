package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.Nickname


class NicknameTypeIntAdapter : ColumnTypeAdapter<Nickname.Type> {

    override fun fromCursor(cursor: Cursor, columnName: String): Nickname.Type {
        val typeInt = cursor.getInt(cursor.getColumnIndex(columnName))
        return Nickname.Type.values().first { it.typeMap == typeInt }
    }

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: Nickname.Type) {
        contentValues.put(columnName, value.typeMap)
    }
}