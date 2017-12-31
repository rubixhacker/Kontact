package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.EmailAddress
import com.hackedcube.kontact.PhoneNumber

class EmailTypeIntAdapter : ColumnTypeAdapter<EmailAddress.Type> {

    override fun fromCursor(cursor: Cursor, columnName: String): EmailAddress.Type {
        val typeInt = cursor.getInt(cursor.getColumnIndex(columnName))
        return EmailAddress.Type.values().first { it.typeMap == typeInt }
    }

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: EmailAddress.Type) {
        contentValues.put(columnName, value.typeMap)
    }
}