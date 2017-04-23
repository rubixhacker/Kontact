package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.EmailAddress
import com.hackedcube.kontact.PhoneNumber

class EmailTypeIntAdapter : ColumnTypeAdapter<EmailAddress.Type> {

    override fun fromCursor(cursor: Cursor, columnName: String): EmailAddress.Type {
        val typeInt by lazy { cursor.getInt(cursor.getColumnIndex(columnName))}
        return EmailAddress.Type.values().filter { it.typeMap == typeInt }.first()
    }

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: EmailAddress.Type) {
        contentValues.put(columnName, value.typeMap)
    }
}