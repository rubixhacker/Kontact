package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.toFlag

class BooleanIntAdapter : ColumnTypeAdapter<Boolean> {
    override fun fromCursor(cursor: Cursor, columnName: String) = cursor.getInt(cursor.getColumnIndex(columnName)).equals(1)

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: Boolean) {
        contentValues.put(columnName, value.toFlag())
    }
}