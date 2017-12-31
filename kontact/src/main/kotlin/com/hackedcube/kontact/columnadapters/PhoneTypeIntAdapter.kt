package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.PhoneNumber

class PhoneTypeIntAdapter : ColumnTypeAdapter<PhoneNumber.Type> {

    override fun fromCursor(cursor: Cursor, columnName: String): PhoneNumber.Type {
        val typeInt = cursor.getInt(cursor.getColumnIndex(columnName))
        return PhoneNumber.Type.values().first { it.typeMap == typeInt }
    }

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: PhoneNumber.Type) {
        contentValues.put(columnName, value.typeMap)
    }
}