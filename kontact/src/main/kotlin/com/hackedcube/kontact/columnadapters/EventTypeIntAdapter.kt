package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.Event


class EventTypeIntAdapter : ColumnTypeAdapter<Event.Type> {

    override fun fromCursor(cursor: Cursor, columnName: String): Event.Type {
        val typeInt = cursor.getInt(cursor.getColumnIndex(columnName))
        return Event.Type.values().first { it.typeMap == typeInt }
    }

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: Event.Type) {
        contentValues.put(columnName, value.typeMap)
    }
}