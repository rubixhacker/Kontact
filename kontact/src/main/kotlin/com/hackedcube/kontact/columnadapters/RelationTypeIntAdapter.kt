package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.Relation

class RelationTypeIntAdapter : ColumnTypeAdapter<Relation.Type> {
    override fun fromCursor(cursor: Cursor, columnName: String): Relation.Type {
        val typeInt = cursor.getInt(cursor.getColumnIndex(columnName))
        return Relation.Type.values().first { it.typeMap == typeInt }
    }

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: Relation.Type) {
        contentValues.put(columnName, value.typeMap)
    }
}