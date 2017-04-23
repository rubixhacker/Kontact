package com.hackedcube.kontact.columnadapters

import android.content.ContentValues
import android.database.Cursor
import android.provider.ContactsContract
import com.gabrielittner.auto.value.cursor.ColumnTypeAdapter
import com.hackedcube.kontact.PhoneNumber
import com.hackedcube.kontact.toFlag



class PhoneTypeIntAdapter : ColumnTypeAdapter<PhoneNumber.Type> {

    override fun fromCursor(cursor: Cursor, columnName: String): PhoneNumber.Type {
        val typeInt by lazy { cursor.getInt(cursor.getColumnIndex(columnName))}
        return PhoneNumber.Type.values().filter { it.typeMap == typeInt }.first()
    }

    override fun toContentValues(contentValues: ContentValues, columnName: String, value: PhoneNumber.Type) {
        contentValues.put(columnName, value.typeMap)
    }
}