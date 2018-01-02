package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.PostalAddress

class PostalAddressTypeIntAdapter : ColumnTypeAdapter<PostalAddress.Type> {

    override fun fromCursor(cursor: Cursor, columnName: String): PostalAddress.Type {
        val typeInt = cursor.getInt(cursor.getColumnIndex(columnName))
        return PostalAddress.Type.values().first { it.typeMap == typeInt }
    }

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: PostalAddress.Type) {
        contentValues.put(columnName, value.typeMap)
    }
}
